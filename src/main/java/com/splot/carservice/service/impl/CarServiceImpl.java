package com.splot.carservice.service.impl;

import com.splot.carservice.model.Car;
import com.splot.carservice.repository.CarRepository;
import com.splot.carservice.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Long id, Car car) {
        car.setId(id);
        return carRepository.save(car);
    }
}
