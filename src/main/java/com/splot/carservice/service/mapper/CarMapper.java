package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.CarRequestDto;
import com.splot.carservice.dto.response.CarResponseDto;
import com.splot.carservice.model.Car;
import org.springframework.stereotype.Service;

@Service
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setManufactureYear(dto.getManufactureYear());
        car.setCarNumber(dto.getCarNumber());
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto responseDto = new CarResponseDto();
        responseDto.setId(car.getId());
        responseDto.setModel(car.getModel());
        responseDto.setManufactureYear(car.getManufactureYear());
        responseDto.setCarNumber(car.getCarNumber());
        return responseDto;
    }
}
