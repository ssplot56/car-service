package com.splot.carservice.service;

import com.splot.carservice.model.MachineComponent;

public interface MachineComponentService {
    MachineComponent save(MachineComponent component);

    MachineComponent update(Long id,MachineComponent component);
}
