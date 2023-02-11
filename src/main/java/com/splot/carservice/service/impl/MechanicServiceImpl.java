package com.splot.carservice.service.impl;

import com.splot.carservice.model.Mechanic;
import com.splot.carservice.model.Order;
import com.splot.carservice.repository.MechanicRepository;
import com.splot.carservice.service.MechanicService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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

    private Double calculateSalary (Mechanic mechanic) {
        List<Order> ordersToBePaid = mechanic.getCompleteOrders().stream()
                .filter(o -> o.getStatus().equals(Order.StatusName.SUCCESSFUL_DONE))
                .collect(Collectors.toList());
        List<Order> allOrders = mechanic.getCompleteOrders();
        allOrders.removeAll(ordersToBePaid);
        double salary = 0.0;
        for (Order order : ordersToBePaid) {
            salary += order.getFinalCost() * 0.4;
            order.setStatus(Order.StatusName.PAID);
            allOrders.add(order);
        }
        mechanic.setCompleteOrders(allOrders);
        mechanicRepository.save(mechanic);
        return salary;
    }
}
