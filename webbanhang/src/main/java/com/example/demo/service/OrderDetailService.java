package com.example.demo.service;

import com.example.demo.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail create(OrderDetail orderDetail);

    List<OrderDetail> getAllOrderDerail();

    List<OrderDetail>getAllProductId(int id);
}
