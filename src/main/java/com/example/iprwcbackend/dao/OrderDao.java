package com.example.iprwcbackend.dao;

import com.example.iprwcbackend.model.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class OrderDao {
    private final OrderRepository orderRepository;
    public OrderDao(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}