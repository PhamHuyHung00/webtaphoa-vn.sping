package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item {
    private  Product product;
    private int quantity;
//    private List<OrderDetail> orderDetails ;

    private String name;
    private String address;
    private String numberphone;
    private String description;

    public Item(String name, String address, String numberphone, String description) {
        this.name = name;
        this.address = address;
        this.numberphone = numberphone;
        this.description = description;
    }

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
