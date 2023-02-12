package com.splot.carservice.controller;

import com.splot.carservice.dto.request.ProductRequestDto;
import com.splot.carservice.dto.response.ProductResponseDto;
import com.splot.carservice.model.Product;
import com.splot.carservice.service.ProductService;
import com.splot.carservice.service.mapper.RequestDtoMapper;
import com.splot.carservice.service.mapper.ResponseDtoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private final RequestDtoMapper<ProductRequestDto, Product> requestMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseMapper;

    public ProductController(ProductService service,
                             RequestDtoMapper<ProductRequestDto, Product> requestMapper,
                             ResponseDtoMapper<ProductResponseDto, Product> responseMapper) {
        this.service = service;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    @ApiOperation("Add a new product")
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = requestMapper.mapToModel(requestDto);
        return responseMapper.mapToDto(service.save(product));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update existing product by id")
    public ProductResponseDto update(@PathVariable Long id,
                                     ProductRequestDto requestDto) {
        Product product = requestMapper.mapToModel(requestDto);
        product.setId(id);
        return responseMapper.mapToDto(service.save(product));
    }
}
