package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/all-green")
public class HomeUserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public String home(@PathVariable Integer id, Model model) {
        model.addAttribute("categoryList", categoryService.getAllCategory());
        model.addAttribute("product", productRepository.getProduct());
        model.addAttribute("products", productRepository.getProductByCateId(id));
        return "homeUser";
    }

    @GetMapping("")
    public String searchProduct(Model model, @RequestParam(name = "name", required = false) String p_name) {
        List<Product> products = null;
        model.addAttribute("categoryList", categoryService.getAllCategory());
        model.addAttribute("product", productRepository.getProduct());

        if (StringUtils.hasText(p_name)) {
            products = productService.getProductByLike(p_name);
        } else {
            products = productService.getAllProduct();
        }
        model.addAttribute("products", products);
        return "homeUser";
    }

    @GetMapping("/detail/{id}")
    public String getProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productRepository.getDetailProduct(id));
        return "detail-product";
    }

    @GetMapping("/tangdan")
    public String getProductByPriceOrderBy(Model model) {
        model.addAttribute("categoryList", categoryService.getAllCategory());
        model.addAttribute("product", productRepository.getProduct());
        model.addAttribute("products", productRepository.getProductByPriceOrderBy());
        return "homeUser";
    }

    @GetMapping("/giamdan")
    public String getProductByPriceOrderByDesc(Model model) {
        model.addAttribute("categoryList", categoryService.getAllCategory());
        model.addAttribute("product", productRepository.getProduct());
        model.addAttribute("products", productRepository.getProductByPriceOrderByDesc());
        return "homeUser";
    }
}
