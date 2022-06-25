package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.put("total", total(session));
        return "cart/giohang";
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(Model model) {
        model.addAttribute("info", new OrderDetail());
        return "cart/info";
    }


    @RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") int id, ModelMap modelMap, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<Order> cart = new ArrayList<>();
            cart.add(new Order(productService.findById(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Order> cart = (List<Order>) session.getAttribute("cart");
            int index = isExists(id, cart);
            if (index == -1) {
                cart.add(new Order(productService.findById(id), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:../../cart";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, HttpSession session) {
        List<Order> cart = (List<Order>) session.getAttribute("cart");
        int index = isExists(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:../../cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, HttpSession session) {
        String[] qa = request.getParameterValues("quantity");
        List<Order> cart = (List<Order>) session.getAttribute("cart");


        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).setQuantity(Integer.parseInt(qa[i]));
        }
        session.setAttribute("cart", cart);
        return "redirect:../cart";
    }

    private double total(HttpSession session) {
        List<Order> cart = (List<Order>) session.getAttribute("cart");
        double s = 0;
        if (cart != null) {
            for (Order item : cart) {
                s += (item.getQuantity())
                        * (item.getProduct().getPrice());
            }

        }
        return s;
    }

    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    public String Order(HttpSession session, OrderDetail orderDetail) {
        List<Order> cart = (List<Order>) session.getAttribute("cart");

        for (Order order : cart) {
            orderDetail.setId(order.getProduct().getId());
            orderDetail.setQuantity(order.getQuantity());
            orderDetail.setPrice(order.getProduct().getPrice());
            orderDetail.setProduct(order.getProduct());
        }
        orderDetailService.create(orderDetail);

        session.removeAttribute("cart");

        return "cart/thanks";
    }


    private int isExists(int id, List<Order> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId() == id) {
                return i;
            }
        }
        return -1;
    }


}
