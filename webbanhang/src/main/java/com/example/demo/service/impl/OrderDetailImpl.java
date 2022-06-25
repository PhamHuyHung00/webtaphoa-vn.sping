package com.example.demo.service.impl;

import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailImpl implements OrderDetailService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return orderRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getAllOrderDerail() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetail> getAllProductId(int id) {
        return orderRepository.searchProductid(id);
    }


}
