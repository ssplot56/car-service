package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.StatusRequestDto;
import com.splot.carservice.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusMapper implements RequestDtoMapper<StatusRequestDto, Order.StatusName> {
    @Override
    public Order.StatusName mapToModel(StatusRequestDto dto) {
        return Order.StatusName.valueOf(dto.getName());
    }
}
