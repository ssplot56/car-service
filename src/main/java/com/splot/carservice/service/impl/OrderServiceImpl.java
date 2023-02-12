package com.splot.carservice.service.impl;

import com.splot.carservice.model.Favor;
import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Product;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.OrderRepository;
import com.splot.carservice.service.OrderService;
import com.splot.carservice.service.OwnerService;
import com.splot.carservice.service.ProductService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OwnerService ownerService;
    private final ProductService productService;
    private static final Double FAVOR_DISCOUNT_PERCENT = 0.01;
    private static final Double PRODUCT_DISCOUNT_PERCENT = 0.02;
    private static final Long DIAGNOSTIC_FAVOR_ID = 1L;

    public OrderServiceImpl(OrderRepository orderRepository, OwnerService ownerService,
                            ProductService productService) {
        this.orderRepository = orderRepository;
        this.ownerService = ownerService;
        this.productService = productService;
    }

    @Override
    public Order save(Order order) {
        order.setAcceptDate(LocalDateTime.now());
        Owner owner = order.getCar().getOwner();
        Order orderWithId = orderRepository.save(order);
        if (!owner.getOrders().contains(orderWithId)) {
            owner.getOrders().add(orderWithId);
            ownerService.save(owner);
        }
        return orderWithId;
    }

    @Override
    public Order update(Long id, Order order) {
        Order oldOrder = getById(id);
        oldOrder.setCar(order.getCar());
        oldOrder.setFavors(order.getFavors());
        oldOrder.setDescription(order.getDescription());
        oldOrder.setProducts(order.getProducts());
        return orderRepository.save(oldOrder);
    }

    @Override
    public Order getById(Long id) {
        return Optional.of(orderRepository.getReferenceById(id))
                .orElseThrow(() -> new RuntimeException("Order with id : " + id + " not found!"));
    }

    @Override
    public Order addProduct(Long id, Product product) {
        Order order = getById(id);
        Product productWithId = productService.save(product);
        order.getProducts().add(productWithId);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(Long id, Order.StatusName status) {
        Order order = getById(id);
        if (status == Order.StatusName.SUCCESSFUL_DONE
                || status == Order.StatusName.UNSUCCESSFUL_DONE) {
            order.setCompleteDate(LocalDateTime.now());
        }
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public Double getFinalCost(Long id) {
        Order order = getById(id);
        Double finalCost = calculateProductsPrice(order)
                + calculateFavorsPrice(order);
        order.setPrice(finalCost);
        orderRepository.save(order);
        return finalCost;
    }

    private Double calculateFavorsPrice(Order order) {
        int numberOfOwnerOrders = order.getCar().getOwner().getOrders().size();
        Double priceWithoutDiscount = order.getFavors().stream()
                .map(Favor::getCost)
                .reduce(0.0, Double::sum);
        return priceWithoutDiscount * (1.0 - numberOfOwnerOrders * FAVOR_DISCOUNT_PERCENT);
    }

    private Double calculateProductsPrice(Order order) {
        int numberOfOwnerOrders = order.getCar().getOwner().getOrders().size();
        double priceWithoutDiscount = 0.0;
        if (order.getProducts().stream().anyMatch(p -> p.getId().equals(DIAGNOSTIC_FAVOR_ID))) {
            if (order.getFavors().size() > 1) {
                priceWithoutDiscount = order.getProducts().stream()
                        .filter(p -> !p.getId().equals(DIAGNOSTIC_FAVOR_ID))
                        .map(Product::getCost)
                        .reduce(0.0, Double::sum);
            } else {
                priceWithoutDiscount = order.getFavors().get(0).getCost();
            }
        }
        return priceWithoutDiscount * (1.0 - numberOfOwnerOrders * PRODUCT_DISCOUNT_PERCENT);
    }
}
