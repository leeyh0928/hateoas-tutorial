package com.example.hateoastutorial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Order extends RepresentationModel<Order> {
    private String orderId;
    private double price;
    private int quantity;
}
