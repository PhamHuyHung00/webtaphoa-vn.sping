package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Table(name = "orderdetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "OrderID", nullable = false)
    private Orders orders;

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
