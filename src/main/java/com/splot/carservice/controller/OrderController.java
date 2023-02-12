package com.splot.carservice.controller;

import com.splot.carservice.dto.request.OrderRequestDto;
import com.splot.carservice.dto.request.ProductRequestDto;
import com.splot.carservice.dto.request.StatusRequestDto;
import com.splot.carservice.dto.response.OrderResponseDto;
import com.splot.carservice.model.Order;
import com.splot.carservice.model.Product;
import com.splot.carservice.service.OrderService;
import com.splot.carservice.service.mapper.RequestDtoMapper;
import com.splot.carservice.service.mapper.ResponseDtoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ResponseDtoMapper<OrderResponseDto, Order> responseMapper;
    private final RequestDtoMapper<OrderRequestDto, Order> requestMapper;
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestMapper;
    private final RequestDtoMapper<StatusRequestDto, Order.StatusName> statusRequestMapper;

    public OrderController(OrderService orderService,
                           ResponseDtoMapper<OrderResponseDto, Order> responseMapper,
                           RequestDtoMapper<OrderRequestDto, Order> requestMapper,
                           RequestDtoMapper<ProductRequestDto, Product> productRequestMapper,
                           RequestDtoMapper<StatusRequestDto, Order.StatusName> statusRequestMapper) {
        this.orderService = orderService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
        this.productRequestMapper = productRequestMapper;
        this.statusRequestMapper = statusRequestMapper;
    }

    @PostMapping
    @ApiOperation("Add a new order")
    public OrderResponseDto create(@RequestBody OrderRequestDto requestDto) {
        Order order = requestMapper.mapToModel(requestDto);
        orderService.save(order);
        return responseMapper.mapToDto(order);
    }

    @PostMapping("/{id}/product")
    @ApiOperation("Add new product to existing order by order id")
    public OrderResponseDto addProduct(@PathVariable Long id,
                                         @RequestBody ProductRequestDto requestDto) {
        Product product = productRequestMapper.mapToModel(requestDto);
        return responseMapper.mapToDto(orderService.addProduct(id, product));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update existing order by id")
    public OrderResponseDto update(@PathVariable Long id,
                                   @RequestBody OrderRequestDto requestDto) {
        Order order = requestMapper.mapToModel(requestDto);
        return responseMapper.mapToDto(orderService.update(id, order));
    }

    @PutMapping("/{id}/status")
    @ApiOperation("Update order status by order id")
    public OrderResponseDto updateStatus(@PathVariable Long id,
                                         @RequestBody StatusRequestDto statusRequestDto) {
        Order.StatusName status = statusRequestMapper.mapToModel(statusRequestDto);
        return responseMapper.mapToDto(orderService.updateOrderStatus(id, status));
    }

    @GetMapping("/{id}/cost")
    @ApiOperation("Get final cost for order by id")
    public Double getFinalCost(@PathVariable Long id) {
        return orderService.getFinalCost(id);
    }
}
