package com.splot.carservice.service;

import com.splot.carservice.model.Mechanic;

public interface MechanicService {
    Mechanic save(Mechanic mechanic);

    Mechanic update(Mechanic mechanic);

    Mechanic getById(Long id);

    Double getSalary(Long id);
}
