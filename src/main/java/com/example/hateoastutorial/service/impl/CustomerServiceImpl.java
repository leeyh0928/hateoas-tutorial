package com.example.hateoastutorial.service.impl;

import com.example.hateoastutorial.api.CustomerController;
import com.example.hateoastutorial.model.Customer;
import com.example.hateoastutorial.service.CustomerService;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer getCustomerDetail(String customerId) {
        Customer customer = Customer.of(customerId, "Jane", "ABC Company");

        Link link = Link.of("http://localhost:8080/spring-security-rest/api/customers/10A");
        customer.add(link);
        customer.add(linkTo(CustomerController.class).slash(customer.getCustomerId()).withSelfRel());

        return customer;
    }

    @Override
    public List<Customer> allCustomers() {
        return Arrays.asList(
                Customer.of("10A", "Jane", "ABC Company"),
                Customer.of("10B", "John", "DEF Company"));
    }
}
