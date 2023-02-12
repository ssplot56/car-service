package com.splot.carservice.service.impl;

import com.splot.carservice.model.Favor;
import com.splot.carservice.model.Mechanic;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.MechanicRepository;
import com.splot.carservice.service.MechanicService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MechanicServiceImpl implements MechanicService {
    private final MechanicRepository mechanicRepository;

    public MechanicServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic getById(Long id) {
        return mechanicRepository.getReferenceById(id);
    }

    @Override
    public Double getSalary(Long id) {
        Mechanic mechanic = mechanicRepository.getReferenceById(id);
        return calculateSalary(mechanic);
    }

    @Override
    public List<Order> getOrders(Long id) {
        return mechanicRepository.getReferenceById(id).getCompleteOrders();
    }

    private Double calculateSalary (Mechanic mechanic) {
        double totalFavorsCost = 0.0;
        List<Order> orders = mechanic.getCompleteOrders();
        for (Order order : orders) {
            for (Favor favor : order.getFavors()) {
                if (favor.getMechanic().equals(mechanic)) {
                    totalFavorsCost += favor.getCost();
                }
            }
        }
        return totalFavorsCost * 0.4;
    }
}
