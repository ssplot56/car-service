package com.splot.carservice.service;

import com.splot.carservice.model.Product;
import com.splot.carservice.model.Order;

public interface OrderService {
    Order save(Order order);

    Order addProduct(Long id, Product component);

    Order update(Long id, Order order);

    Order updateOrderStatus(Long id, Order.StatusName status);

    Double getFinalCost(Long id);

    Order getById(Long id);
}
