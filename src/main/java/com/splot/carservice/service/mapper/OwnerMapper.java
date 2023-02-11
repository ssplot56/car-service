package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.CarOwnerRequestDto;
import com.splot.carservice.dto.response.CarOwnerResponseDto;
import com.splot.carservice.model.Car;
import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Order;
import com.splot.carservice.service.CarService;
import com.splot.carservice.service.OrderService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class OwnerMapper implements RequestDtoMapper<CarOwnerRequestDto,
        Owner>, ResponseDtoMapper<CarOwnerResponseDto, Owner> {
    private final CarService carService;
    private final OrderService orderService;

    public OwnerMapper(CarService carService, OrderService orderService) {
        this.carService = carService;
        this.orderService = orderService;
    }

    @Override
    public Owner mapToModel(CarOwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setFullName(dto.getFullName());

        return owner;
    }

    @Override
    public CarOwnerResponseDto mapToDto(Owner owner) {
        CarOwnerResponseDto responseDto = new CarOwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setFullName(owner.getFullName());
        responseDto.setCarIds(owner.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        responseDto.setOrderIds(owner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
