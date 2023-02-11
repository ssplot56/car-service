package com.splot.carservice.controller;

import com.splot.carservice.dto.request.MechanicRequestDto;
import com.splot.carservice.dto.response.MechanicResponseDto;
import com.splot.carservice.dto.response.OrderResponseDto;
import com.splot.carservice.model.Mechanic;
import com.splot.carservice.model.Order;
import com.splot.carservice.service.MechanicService;
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
@RequestMapping("/mechanics")
public class MechanicController {
    private final MechanicService mechanicService;
    private final ResponseDtoMapper<MechanicResponseDto, Mechanic> responseMapper;
    private final RequestDtoMapper<MechanicRequestDto, Mechanic> requestMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> orderResponseMapper;

    public MechanicController(MechanicService mechanicService,
                              ResponseDtoMapper<MechanicResponseDto, Mechanic> responseMapper,
                              RequestDtoMapper<MechanicRequestDto, Mechanic> requestDtoMapper,
                              ResponseDtoMapper<OrderResponseDto, Order> orderResponseMapper) {
        this.mechanicService = mechanicService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestDtoMapper;
        this.orderResponseMapper = orderResponseMapper;
    }

    @PostMapping
    public MechanicResponseDto save(@RequestBody MechanicRequestDto requestDto) {
        Mechanic mechanic = mechanicService.save(requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(mechanic);
    }

    @PutMapping("/{id}")
    public MechanicResponseDto update(@PathVariable Long id,
                                      @RequestBody MechanicRequestDto requestDto) {
        Mechanic mechanic = requestMapper.mapToModel(requestDto);
        mechanic.setId(id);
        mechanicService.save(mechanic);
        return responseMapper.mapToDto(mechanic);
    }

    @GetMapping("/{id}")
    public List<OrderResponseDto> getMechanicOrders(@PathVariable Long id) {
        return mechanicService.getById(id).getCompleteOrders().stream()
                .map(orderResponseMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping("/get-salary/{id}")
    public Double getSalary(@PathVariable Long id) {
        return mechanicService.getSalary(id);
    }
}
