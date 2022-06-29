package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    List<Product> getProductByLike(String p_name);

    Product addProduct(ProductRequest productRequest);


    Product findById(int id);

    void save(int id, Product product);

    void delete(int id);


}
