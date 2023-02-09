package com.splot.carservice.service;

import com.splot.carservice.model.Favor;

public interface FavorService {
    Favor save(Favor favor);

    Favor getById(Long id);

    Favor update(Long id, Favor favor);

    Favor updateStatus(Long id, String status);
}
