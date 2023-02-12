package com.splot.carservice.controller;

import com.splot.carservice.dto.request.CarRequestDto;
import com.splot.carservice.dto.response.CarResponseDto;
import com.splot.carservice.model.Car;
import com.splot.carservice.service.CarService;
import com.splot.carservice.service.mapper.RequestDtoMapper;
import com.splot.carservice.service.mapper.ResponseDtoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation("Add a new car")
    public CarResponseDto save(@RequestBody CarRequestDto requestDto) {
        Car car = carService.save(requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(car);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update existing car by id")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody CarRequestDto requestDto) {
        Car car = requestMapper.mapToModel(requestDto);
        car.setId(id);
        carService.save(car);
        return responseMapper.mapToDto(car);
    }
}
