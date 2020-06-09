package com.example.hateoastutorial.service;

import com.example.hateoastutorial.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerDetail(String customerId);
    List<Customer> allCustomers();
}
