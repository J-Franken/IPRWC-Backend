package com.example.iprwcbackend.dao;

public class OrderItemDao {
    private final OrderItemRepository orderItemRepository;

    public OrderItemDao(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
}
