package com.example.hateoastutorial.service.impl;

import com.example.hateoastutorial.model.Order;
import com.example.hateoastutorial.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrderDetail(String customerId, String orderId) {
        return Order.of(customerId + "-" + orderId, 2.5, 5);
    }

    @Override
    public List<Order> getAllOrdersForCustomer(String customerId) {
        return Arrays.asList(
                Order.of(customerId + "-1O", 2.5d, 5),
                Order.of(customerId + "-2O", 3.5d, 10));
    }
}
