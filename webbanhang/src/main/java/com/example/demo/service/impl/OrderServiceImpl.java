package com.example.demo.service.impl;

import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Orders create(Orders orders) {
        return orderRepository.save(orders);

    }

}
