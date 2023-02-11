package com.splot.carservice.service.impl;

import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.OwnerRepository;
import com.splot.carservice.service.OwnerService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getById(Long id) {
        return ownerRepository.getReferenceById(id);
    }

    @Override
    public Owner update(Long id, Owner owner) {
        owner.setId(id);
        return ownerRepository.save(owner);
    }

    @Override
    public List<Order> getOrders(Long id) {
        return ownerRepository.getReferenceById(id).getOrders();
    }
}
