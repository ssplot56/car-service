package com.splot.carservice.service.impl;

import com.splot.carservice.model.Product;
import com.splot.carservice.repository.ProductRepository;
import com.splot.carservice.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product component) {
        return productRepository.save(component);
    }

    @Override
    public Product update(Long id, Product component) {
        component.setId(id);
        return productRepository.save(component);
    }

    @Override
    public Product getById(Long id) {
        return Optional.of(productRepository.getReferenceById(id))
                .orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found!"));
    }
}
