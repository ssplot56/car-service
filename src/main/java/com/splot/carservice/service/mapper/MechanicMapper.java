package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.MechanicRequestDto;
import com.splot.carservice.dto.response.MechanicResponseDto;
import com.splot.carservice.model.Mechanic;
import com.splot.carservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class MechanicMapper implements RequestDtoMapper<MechanicRequestDto, Mechanic>,
        ResponseDtoMapper<MechanicResponseDto, Mechanic> {
    @Override
    public Mechanic mapToModel(MechanicRequestDto dto) {
        Mechanic mechanic = new Mechanic();
        mechanic.setFullName(dto.getFullName());
        mechanic.setCompleteOrders(new ArrayList<>());
        return mechanic;
    }

    @Override
    public MechanicResponseDto mapToDto(Mechanic mechanic) {
        MechanicResponseDto responseDto = new MechanicResponseDto();
        responseDto.setId(mechanic.getId());
        responseDto.setFullName(mechanic.getFullName());
        responseDto.setCompleteOrderIds(mechanic.getCompleteOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
