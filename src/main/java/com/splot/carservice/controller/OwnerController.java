package com.splot.carservice.controller;

import com.splot.carservice.dto.request.OwnerRequestDto;
import com.splot.carservice.dto.response.OrderResponseDto;
import com.splot.carservice.dto.response.OwnerResponseDto;
import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Order;
import com.splot.carservice.service.OwnerService;
import com.splot.carservice.service.mapper.RequestDtoMapper;
import com.splot.carservice.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final RequestDtoMapper<OwnerRequestDto, Owner> requestMapper;
    private final ResponseDtoMapper<OwnerResponseDto, Owner> responseMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseMapper;

    public OwnerController(OwnerService ownerService,
                           RequestDtoMapper<OwnerRequestDto, Owner> requestMapper,
                           ResponseDtoMapper<OwnerResponseDto, Owner> responseMapper,
                           ResponseDtoMapper<OrderResponseDto, Order> orderResponseMapper) {
        this.ownerService = ownerService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.orderResponseMapper = orderResponseMapper;
    }

    @PostMapping
    public OwnerResponseDto save(@RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerService.save(requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(owner);
    }

    @PutMapping("/{id}")
    public OwnerResponseDto update(@PathVariable Long id,
                                   @RequestBody OwnerRequestDto requestDto) {
        Owner owner = requestMapper.mapToModel(requestDto);
        owner.setId(id);
        return responseMapper.mapToDto(ownerService.save(owner));
    }

    @GetMapping("/{id}/orders")
    public List<OrderResponseDto> getOwnerOrders(@PathVariable Long id) {
        List<Order> orders = ownerService.getOrders(id);
        return orders.stream()
                .map(orderResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
