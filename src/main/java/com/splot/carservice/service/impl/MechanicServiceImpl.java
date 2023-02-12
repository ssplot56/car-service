package com.splot.carservice.service.impl;

import com.splot.carservice.model.Favor;
import com.splot.carservice.model.Mechanic;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.MechanicRepository;
import com.splot.carservice.service.MechanicService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MechanicServiceImpl implements MechanicService {
    private final MechanicRepository mechanicRepository;
    private static final Double SALARY_PERCENT = 0.4;

    public MechanicServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic getById(Long id) {
        return Optional.of(mechanicRepository.getReferenceById(id))
                .orElseThrow(() -> new RuntimeException("Mechanic with id:" + id + " not found!"));
    }

    @Override
    public Double getSalary(Long id) {
        Mechanic mechanic = getById(id);
        return calculateSalary(mechanic);
    }

    @Override
    public List<Order> getOrders(Long id) {
        return getById(id).getCompleteOrders();
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
        return totalFavorsCost * SALARY_PERCENT;
    }
}
