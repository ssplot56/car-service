package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.OrderRequestDto;
import com.splot.carservice.dto.response.OrderResponseDto;
import com.splot.carservice.model.Favor;
import com.splot.carservice.model.Product;
import com.splot.carservice.model.Order;
import com.splot.carservice.service.CarService;
import com.splot.carservice.service.FavorService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        OrderRequestDto requestDto = new OrderRequestDto();
        return null;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        return null;
    }
}
