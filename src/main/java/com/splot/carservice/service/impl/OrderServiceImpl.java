package com.splot.carservice.service.impl;

import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Product;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.OrderRepository;
import com.splot.carservice.service.OwnerService;
import com.splot.carservice.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OwnerService ownerService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OwnerService ownerService) {
        this.orderRepository = orderRepository;
        this.ownerService = ownerService;
    }

    @Override
    public Order save(Order order) {
        Order savedOrder = orderRepository.save(order);
        Owner owner = savedOrder.getCar().getOwner();
        owner.getOrders().add(savedOrder);
        ownerService.save(owner);
        return savedOrder;
    }

    @Override
    public Order addMachineComponent(Long id, Product component) {
        Order order = orderRepository.getReferenceById(id);


        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.getReferenceById(id);
        // TODO: 09.02.2023 create new service that may predicate 
        order.setStatus(Order.StatusName.IN_PROCESS);
        return orderRepository.save(order);
    }

    @Override
    public Order getFinalCost(Long id) {

        return null;
    }
}
