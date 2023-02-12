package com.splot.carservice.service.impl;

import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.OwnerRepository;
import com.splot.carservice.service.OwnerService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        return Optional.of(ownerRepository.getReferenceById(id))
                .orElseThrow(() -> new RuntimeException("Owner with id: " + id + " not found!"));
    }

    @Override
    public Owner update(Long id, Owner owner) {
        owner.setId(id);
        return ownerRepository.save(owner);
    }

    @Override
    public List<Order> getOrders(Long id) {
        return Optional.of(ownerRepository.getReferenceById(id).getOrders())
                .orElseThrow(() -> new RuntimeException("Owner with id: " + id + "not found!"));
    }
}
