package com.splot.carservice.service;

import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Order;
import java.util.List;

public interface OwnerService {
    Owner save(Owner owner);

    Owner getById(Long id);

    Owner update(Long id, Owner owner);

    List<Order> getOrders(Long id);
}
