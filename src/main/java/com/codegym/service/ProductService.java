package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {

    private static final Map<Long, Product> products;

    static {
        products = new HashMap<>();
        products.put(1L, new Product(1L, "iphone 15 pro max", "1TB", 44990000));
        products.put(2L, new Product(2L, "iphone 15 pro max", "512GB", 37990000));
        products.put(3L, new Product(3L, "iphone 15 pro max", "256GB", 33990000));
        products.put(4L, new Product(4L, "iphone 15 pro max", "128GB", 29990000));
    }

    @Override
    public List<Product> productList() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().equalsIgnoreCase(name)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    @Override
    public Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public void delete(Long id) {
        products.remove(id);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }
}
