package com.example.iprwcbackend.dao;

public class OrderDao {
    private final OrderRepository orderRepository;

    public OrderDao(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
