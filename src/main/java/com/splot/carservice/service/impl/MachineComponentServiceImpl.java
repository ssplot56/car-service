package com.splot.carservice.service.impl;

import com.splot.carservice.model.MachineComponent;
import com.splot.carservice.repository.MachineComponentRepository;
import com.splot.carservice.service.MachineComponentService;
import org.springframework.stereotype.Service;

@Service
public class MachineComponentServiceImpl implements MachineComponentService {
    private final MachineComponentRepository componentRepository;

    public MachineComponentServiceImpl(MachineComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public MachineComponent save(MachineComponent component) {
        return componentRepository.save(component);
    }

    @Override
    public MachineComponent update(Long id, MachineComponent component) {
        component.setId(id);
        return componentRepository.save(component);
    }
}
