package com.example.iprwcbackend.controller;

import com.example.iprwcbackend.dao.OrderDao;
import com.example.iprwcbackend.model.ApiResponse;
import com.example.iprwcbackend.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/orders")
@CrossOrigin(origins = "${frontend_url}")
public class OrderController {

    private final OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @GetMapping
    @ResponseBody
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Order getOrderById(@PathVariable Integer id) {
        return orderDao.getOrderById(id);
    }

    @PostMapping
    @ResponseBody
    public ApiResponse addOrder(@RequestBody Order order) {
        orderDao.addOrder(order);
        return new ApiResponse(HttpStatus.ACCEPTED, "Order added successfully");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteOrder(@PathVariable Integer id) {
        orderDao.deleteOrder(id);
    }
    }
