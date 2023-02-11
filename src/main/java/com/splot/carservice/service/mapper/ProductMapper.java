package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.ProductRequestDto;
import com.splot.carservice.dto.response.ProductResponseDto;
import com.splot.carservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setCost(dto.getCost());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setCost(product.getCost());
        return responseDto;
    }
}
