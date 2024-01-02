package com.codegym.repository;

import com.codegym.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(int id);

    void save(Product product);
}
