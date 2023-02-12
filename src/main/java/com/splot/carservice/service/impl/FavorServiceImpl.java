package com.splot.carservice.service.impl;

import com.splot.carservice.model.Favor;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.FavorRepository;
import com.splot.carservice.service.FavorService;
import com.splot.carservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavorServiceImpl implements FavorService {
    private final FavorRepository favorRepository;
    private final OrderService orderService;

    public FavorServiceImpl(FavorRepository favorRepository,
                            OrderService orderService) {
        this.favorRepository = favorRepository;
        this.orderService = orderService;
    }

    @Override
    public Favor save(Favor favor) {
        Order order = favor.getOrder();
        Favor favorWithId = favorRepository.save(favor);
        if (!order.getFavors().contains(favor)) {
            order.getFavors().add(favor);
            orderService.save(order);
        }
        return favorWithId;
    }

    @Override
    public Favor getById(Long id) {
        return Optional.of(favorRepository.getReferenceById(id))
                .orElseThrow(() -> new RuntimeException("Favor with id: " + id + " not found!"));
    }

    @Override
    public Favor update(Long id, Favor favor) {
        favor.setId(id);
        return favorRepository.save(favor);
    }
}
