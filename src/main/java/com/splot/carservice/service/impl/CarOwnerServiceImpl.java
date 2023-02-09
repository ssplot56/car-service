package com.splot.carservice.service.impl;

import com.splot.carservice.model.CarOwner;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.CarOwnerRepository;
import com.splot.carservice.service.CarOwnerService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    private final CarOwnerRepository ownerRepository;

    public CarOwnerServiceImpl(CarOwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public CarOwner save(CarOwner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public CarOwner update(Long id, CarOwner owner) {
        owner.setId(id);
        return ownerRepository.save(owner);
    }

    @Override
    public List<Order> getOrders(Long id) {
        return ownerRepository.getReferenceById(id).getOrders();
    }
}
