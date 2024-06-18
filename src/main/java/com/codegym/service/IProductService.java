package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> productList();

    List<Product> findByName(String name);

    Product findById(Long id);

    void delete(Long id);

    void save(Product product);
}
