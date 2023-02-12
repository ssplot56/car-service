package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.OwnerRequestDto;
import com.splot.carservice.dto.response.OwnerResponseDto;
import com.splot.carservice.model.Car;
import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Order;
import com.splot.carservice.service.CarService;
import com.splot.carservice.service.OrderService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class OwnerMapper implements RequestDtoMapper<OwnerRequestDto,
        Owner>, ResponseDtoMapper<OwnerResponseDto, Owner> {
    private final CarService carService;
    private final OrderService orderService;

    public OwnerMapper(CarService carService, OrderService orderService) {
        this.carService = carService;
        this.orderService = orderService;
    }

    @Override
    public Owner mapToModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setFullName(dto.getFullName());
        owner.setCars(dto.getCarIds().stream()
                .map(carService::getById)
                .collect(Collectors.toList()));
        owner.setOrders(dto.getOrderIds().stream()
                .map(orderService::getById)
                .collect(Collectors.toList()));
        return owner;
    }

    @Override
    public OwnerResponseDto mapToDto(Owner owner) {
        OwnerResponseDto responseDto = new OwnerResponseDto();
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
