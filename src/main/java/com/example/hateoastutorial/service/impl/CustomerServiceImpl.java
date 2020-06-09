package com.example.hateoastutorial.service.impl;

import com.example.hateoastutorial.model.Customer;
import com.example.hateoastutorial.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer getCustomerDetail(String customerId) {
        return Customer.of(customerId, "Jane", "ABC Company");
    }

    @Override
    public List<Customer> allCustomers() {
        return Arrays.asList(
                Customer.of("10A", "Jane", "ABC Company"),
                Customer.of("10B", "John", "DEF Company"));
    }
}
