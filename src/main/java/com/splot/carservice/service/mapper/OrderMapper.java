package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.OrderRequestDto;
import com.splot.carservice.dto.response.OrderResponseDto;
import com.splot.carservice.model.Favor;
import com.splot.carservice.model.Order;
import com.splot.carservice.model.Product;
import com.splot.carservice.service.CarService;
import com.splot.carservice.service.FavorService;
import com.splot.carservice.service.ProductService;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;
    private final FavorService favorService;
    private final ProductService productService;

    public OrderMapper(CarService carService,
                       FavorService favorService,
                       ProductService productService) {
        this.carService = carService;
        this.favorService = favorService;
        this.productService = productService;
    }

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.getById(dto.getCarId()));
        order.setDescription(dto.getProblemDescription());
        order.setFavors(dto.getFavorIds().stream()
                .map(favorService::getById)
                .collect(Collectors.toList()));
        order.setProducts(dto.getProductIds().stream()
                .map(productService::getById)
                .collect(Collectors.toList()));
        order.setStatus(dto.getStatus());
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setCarId(order.getCar().getId());
        responseDto.setProblemDescription(order.getDescription());
        responseDto.setAcceptDate(order.getAcceptDate());
        responseDto.setFavorIds(order.getFavors().stream()
                .map(Favor::getId)
                .collect(Collectors.toList()));
        responseDto.setProductIds(order.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        responseDto.setStatus(order.getStatus());
        responseDto.setPrice(order.getPrice());
        responseDto.setCompleteDate(order.getCompleteDate());
        return responseDto;
    }
}
