package com.example.hateoastutorial.service;

import com.example.hateoastutorial.model.Order;

import java.util.List;

public interface OrderService {
    Order getOrderDetail(String customerId, String orderId);
    List<Order> getAllOrdersForCustomer(String customerId);
}
