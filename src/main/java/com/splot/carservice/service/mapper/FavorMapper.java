package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.FavorRequestDto;
import com.splot.carservice.dto.response.FavorResponseDto;
import com.splot.carservice.model.Favor;
import com.splot.carservice.repository.MechanicRepository;
import com.splot.carservice.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class FavorMapper implements RequestDtoMapper<FavorRequestDto, Favor>,
        ResponseDtoMapper<FavorResponseDto, Favor> {
    private final OrderRepository orderRepository;
    private final MechanicRepository mechanicRepository;

    public FavorMapper(OrderRepository orderRepository,
                       MechanicRepository mechanicRepository) {
        this.orderRepository = orderRepository;
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Favor mapToModel(FavorRequestDto dto) {
        Favor favor = new Favor();
        favor.setOrder(orderRepository.getReferenceById(dto.getOrderId()));
        favor.setMechanic(mechanicRepository.getReferenceById(dto.getMechanicId()));
        favor.setCost(dto.getCost());
        favor.setStatus(dto.getStatus());
        return favor;
    }

    @Override
    public FavorResponseDto mapToDto(Favor favor) {
        FavorResponseDto responseDto = new FavorResponseDto();
        responseDto.setId(favor.getId());
        responseDto.setOrderId(favor.getOrder().getId());
        responseDto.setMechanicId(favor.getMechanic().getId());
        responseDto.setCost(favor.getCost());
        responseDto.setStatus(favor.getStatus());
        return responseDto;
    }
}
