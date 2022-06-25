package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "orderdetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "userid", nullable = false)
//    private User user;

    private double price;
    private int quantity;

    private String name;
    private String address;
    private String numberphone;
    private String description;

    public OrderDetail(String name, String address, String numberphone, String description) {
        this.name = name;
        this.address = address;
        this.numberphone = numberphone;
        this.description = description;
    }

    public OrderDetail(int id, Product product, double price, int quantity) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }
}
