package com.splot.carservice.controller;

import com.splot.carservice.dto.request.CarOwnerRequestDto;
import com.splot.carservice.dto.response.CarOwnerResponseDto;
import com.splot.carservice.model.Owner;
import com.splot.carservice.model.Order;
import com.splot.carservice.service.OwnerService;
import com.splot.carservice.service.mapper.RequestDtoMapper;
import com.splot.carservice.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final RequestDtoMapper<CarOwnerRequestDto, Owner> requestMapper;
    private final ResponseDtoMapper<CarOwnerResponseDto, Owner> responseMapper;

    public OwnerController(OwnerService ownerService,
                           RequestDtoMapper<CarOwnerRequestDto, Owner> requestMapper,
                           ResponseDtoMapper<CarOwnerResponseDto, Owner> responseMapper) {
        this.ownerService = ownerService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public CarOwnerResponseDto save(@RequestBody CarOwnerRequestDto requestDto) {
        Owner owner = ownerService.save(requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(owner);
    }

    @PutMapping("/{id}")
    public CarOwnerResponseDto update(@PathVariable Long id,
                                      @RequestBody CarOwnerRequestDto requestDto) {
        List<Order> orders = ownerService.getById(id).getOrders();
        Owner owner = requestMapper.mapToModel(requestDto);
        owner.setId(id);
        owner.setOrders(orders);
        ownerService.save(owner);
        return responseMapper.mapToDto(owner);
    }
}
