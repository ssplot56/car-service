package com.splot.carservice.service.impl;

import com.splot.carservice.model.Favor;
import com.splot.carservice.repository.FavorRepository;
import com.splot.carservice.service.FavorService;
import org.springframework.stereotype.Service;

@Service
public class FavorServiceImpl implements FavorService {
    private final FavorRepository favorRepository;

    public FavorServiceImpl(FavorRepository favorRepository) {
        this.favorRepository = favorRepository;
    }

    @Override
    public Favor save(Favor favor) {
        return favorRepository.save(favor);
    }

    @Override
    public Favor getById(Long id) {
        return favorRepository.getReferenceById(id);
    }

    @Override
    public Favor update(Long id, Favor favor) {
        favor.setId(id);
        return favorRepository.save(favor);
    }
}
