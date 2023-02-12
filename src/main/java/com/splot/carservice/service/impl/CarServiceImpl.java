package com.splot.carservice.service.impl;

import com.splot.carservice.model.Car;
import com.splot.carservice.model.Owner;
import com.splot.carservice.repository.CarRepository;
import com.splot.carservice.service.CarService;
import com.splot.carservice.service.OwnerService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final OwnerService ownerService;

    public CarServiceImpl(CarRepository carRepository, OwnerService ownerService) {
        this.carRepository = carRepository;
        this.ownerService = ownerService;
    }

    @Override
    public Car save(Car car) {
        Owner owner = car.getOwner();
        Car carWithId = carRepository.save(car);
        if (!owner.getCars().contains(carWithId)) {
            owner.getCars().add(carWithId);
            ownerService.save(owner);
        }
        return carWithId;
    }

    @Override
    public Car getById(Long id) {
        return Optional.of(carRepository.getReferenceById(id))
                .orElseThrow(() -> new RuntimeException("Car with id: " + id + " not found!"));
    }
}
