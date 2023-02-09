package com.splot.carservice.service.impl;

import com.splot.carservice.model.Favor;
import com.splot.carservice.model.MachineComponent;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.OrderRepository;
import com.splot.carservice.service.OrderService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order addMachineComponent(Long id, MachineComponent component) {
        Order order = orderRepository.getReferenceById(id);
        List<MachineComponent> components = order.getComponents();
        components.add(component);
        order.setComponents(components);
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
        Order order = orderRepository.getReferenceById(id);
        double finalCost = 0.0;
        for (MachineComponent component : order.getComponents()) {
            finalCost += component.getCost();
        }
        for (Favor favor : order.getFavors()) {
            finalCost += favor.getCost();
        }
        order.setFinalCost(finalCost);
        return orderRepository.save(order);
    }
}
