package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String logoPath;
    private double price;
    private String detail;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Collection<OrderDetail> orderDetails;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryId", nullable = false)
    private Category category;


}

