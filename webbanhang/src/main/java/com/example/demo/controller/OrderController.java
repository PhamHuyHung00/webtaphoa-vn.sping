package com.example.demo.controller;

import com.example.demo.entity.OrderDetail;
import com.example.demo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("list-order")
    public String getAllOrder(Model model, @RequestParam(name = "id", required = false) Integer id) {
        List<OrderDetail> orderDetailList = null;
        if(id != null) {
            orderDetailList = orderDetailService.getAllProductId(id);
        } else {
            orderDetailList = orderDetailService.getAllOrderDerail();
        }
        model.addAttribute("orderList", orderDetailList);
        return "orderListAd";
    }
}
