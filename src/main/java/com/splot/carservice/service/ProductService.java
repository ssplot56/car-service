package com.splot.carservice.service;

import com.splot.carservice.model.Product;

public interface ProductService {
    Product save(Product component);

    Product update(Long id, Product component);

    Product getById(Long id);
}
