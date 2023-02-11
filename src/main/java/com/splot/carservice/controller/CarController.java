package com.splot.carservice.controller;

import com.splot.carservice.dto.request.CarRequestDto;
import com.splot.carservice.dto.response.CarResponseDto;
import com.splot.carservice.model.Car;
import com.splot.carservice.service.CarService;
import com.splot.carservice.service.mapper.RequestDtoMapper;
import com.splot.carservice.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final ResponseDtoMapper<CarResponseDto, Car> responseMapper;
    private final RequestDtoMapper<CarRequestDto, Car> requestMapper;

    public CarController(CarService carService,
                         ResponseDtoMapper<CarResponseDto, Car> responseMapper,
                         RequestDtoMapper<CarRequestDto, Car> requestMapper) {
        this.carService = carService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public CarResponseDto save(@RequestBody CarRequestDto requestDto) {
        Car car = carService.save(requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(car);
    }

    @PutMapping("/{id}")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody CarRequestDto requestDto) {
        Car car = requestMapper.mapToModel(requestDto);
        car.setId(id);
        carService.save(car);
        return responseMapper.mapToDto(car);
    }
}
