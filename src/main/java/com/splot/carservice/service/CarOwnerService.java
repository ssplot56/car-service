package com.splot.carservice.service;

import com.splot.carservice.model.CarOwner;
import com.splot.carservice.model.Order;
import java.util.List;

public interface CarOwnerService {
    CarOwner save(CarOwner owner);

    CarOwner getById(Long id);

    CarOwner update(Long id, CarOwner owner);

    List<Order> getOrders(Long id);
}
