package com.splot.carservice.service;

import com.splot.carservice.model.Mechanic;
import com.splot.carservice.model.Order;
import java.util.List;

public interface MechanicService {
    Mechanic save(Mechanic mechanic);

    Mechanic getById(Long id);

    Double getSalary(Long id);

    List<Order> getOrders(Long id);
}
