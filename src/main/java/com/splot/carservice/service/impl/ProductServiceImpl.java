package com.splot.carservice.service.impl;

import com.splot.carservice.model.Product;
import com.splot.carservice.repository.ProductRepository;
import com.splot.carservice.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository componentRepository;

    public ProductServiceImpl(ProductRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public Product save(Product component) {
        return componentRepository.save(component);
    }

    @Override
    public Product update(Long id, Product component) {
        component.setId(id);
        return componentRepository.save(component);
    }
}
