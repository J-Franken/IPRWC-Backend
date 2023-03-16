package com.example.iprwcbackend.dao;

public class ProductDao {
    private final ProductRepository productRepository;

    public ProductDao(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
