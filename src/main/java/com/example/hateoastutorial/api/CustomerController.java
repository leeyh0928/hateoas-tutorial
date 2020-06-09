package com.example.hateoastutorial.api;

import com.example.hateoastutorial.model.Customer;
import com.example.hateoastutorial.model.Order;
import com.example.hateoastutorial.service.CustomerService;
import com.example.hateoastutorial.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerDetail(customerId);
    }

    @GetMapping("/{customerId}/{orderId}")
    public Order getOrderById(@PathVariable String customerId, @PathVariable String orderId) {
        return orderService.getOrderDetail(customerId, orderId);
    }

    @GetMapping(value = "/{customerId}/orders", produces = { "application/hal+json" })
    public CollectionModel<Order> getOrdersForCustomer(@PathVariable final String customerId) {
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
        for (final Order order : orders) {
            Link selfLink = linkTo(methodOn(CustomerController.class)
                    .getOrderById(customerId, order.getOrderId()))
                    .withSelfRel();

            order.add(selfLink);
        }

        Link link = linkTo(methodOn(CustomerController.class)
                .getOrdersForCustomer(customerId))
                .withSelfRel();

        return CollectionModel.of(orders, link);
    }

    @GetMapping(produces = { "application/hal+json" })
    public CollectionModel<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerService.allCustomers();

        for (Customer customer : allCustomers) {
            String customerId = customer.getCustomerId();
            Link selfLink = linkTo(CustomerController.class)
                    .slash(customerId)
                    .withSelfRel();

            customer.add(selfLink);

            if (orderService.getAllOrdersForCustomer(customerId).size() > 0) {
                Link ordersLink = linkTo(methodOn(CustomerController.class)
                        .getOrdersForCustomer(customerId)).withRel("allOrders");

                customer.add(ordersLink);
            }
        }

        Link link = linkTo(CustomerController.class)
                .withSelfRel();

        return CollectionModel.of(allCustomers, link);
    }
}
