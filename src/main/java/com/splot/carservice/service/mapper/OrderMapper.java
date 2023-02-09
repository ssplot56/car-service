package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.OrderRequestDto;
import com.splot.carservice.dto.response.OrderResponseDto;
import com.splot.carservice.model.Favor;
import com.splot.carservice.model.MachineComponent;
import com.splot.carservice.model.Order;
import com.splot.carservice.service.CarService;
import com.splot.carservice.service.FavorService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;
    private final FavorService favorService;

    public OrderMapper(CarService carService, FavorService favorService) {
        this.carService = carService;
        this.favorService = favorService;
    }

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.getById(dto.getCarId()));
        order.setProblemDescription(dto.getProblemDescription());
        order.setAcceptDate(LocalDateTime.now());
        order.setFavors(dto.getFavorIds().stream()
                .map(favorService::getById)
                .collect(Collectors.toList()));
        order.setStatus(Order.StatusName.ACCEPT);
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setCarId(order.getId());
        responseDto.setProblemDescription(order.getProblemDescription());
        responseDto.setAcceptDate(order.getAcceptDate());
        responseDto.setFavorIds(order.getFavors().stream()
                .map(Favor::getId)
                .collect(Collectors.toList()));
        responseDto.setComponentIds(order.getComponents().stream()
                .map(MachineComponent::getId)
                .collect(Collectors.toList()));
        responseDto.setStatus(order.getStatus().name());
        responseDto.setFinalCost(order.getFinalCost());
        responseDto.setCompleteDate(order.getCompleteDate());
        return responseDto;
    }
}
