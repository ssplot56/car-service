package com.splot.carservice.service;

import com.splot.carservice.model.Car;

public interface CarService {
    Car save(Car car);

    Car update(Long id, Car car);

    Car getById(Long id);
}
