package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.CarRequestDto;
import com.splot.carservice.dto.response.CarResponseDto;
import com.splot.carservice.model.Car;
import com.splot.carservice.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    private final OwnerService ownerService;

    public CarMapper(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setManufactureYear(dto.getManufactureYear());
        car.setCarNumber(dto.getCarNumber());
        car.setOwner(ownerService.getById(dto.getOwnerId()));
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto responseDto = new CarResponseDto();
        responseDto.setId(car.getId());
        responseDto.setModel(car.getModel());
        responseDto.setManufactureYear(car.getManufactureYear());
        responseDto.setCarNumber(car.getCarNumber());
        responseDto.setOwnerId(car.getOwner().getId());
        return responseDto;
    }
}
