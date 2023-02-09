package com.splot.carservice.service;

import com.splot.carservice.model.MachineComponent;
import com.splot.carservice.model.Order;

public interface OrderService {
    Order save(Order order);

    Order addMachineComponent(Long id, MachineComponent component);

    Order update(Long id, Order order);

    Order updateOrderStatus(Long id, String status);

    Order getFinalCost(Long id);
}
