package com.example.demo.controller;


import com.example.demo.dto.ProductRequest;

import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageSource messageSource;


//    @GetMapping
//    public String listAllEmployers(Model model) {
//        model.addAttribute("products", productService.getAllProduct());
//        return "homead";
//    }

    @GetMapping
    public String searchProduct( Model model, @RequestParam(name = "name", required = false) String p_name) {
        List<Product> products = null;

        if (StringUtils.hasText(p_name)) {
            products = productService.getProductByLike(p_name);
        } else {
            products = productService.getAllProduct();
        }
        model.addAttribute("products", products);
        return "homead";
    }

    @GetMapping("/add")
    public String showAddEmployerForm(Model model) {
        model.addAttribute("productForm", new ProductRequest());
        model.addAttribute("category", categoryService.getAllCategory());
        return "add-product";
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public String addEmployer(@Valid @ModelAttribute("productForm") ProductRequest productRequest,
                              BindingResult result, Locale locale, Model model) {
        if (productRequest.getLogo().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("productForm", "logo", messageSource.getMessage("product.logo.notblank", null, locale)));
        }
        if (result.hasErrors()) {
            return "add-product";
        }

        productService.addProduct(productRequest);
        return "redirect:/admin";
    }


    @GetMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") int id) {

        productService.delete(id);
        return "redirect:/admin";
    }


    @GetMapping("/editProd/{id}")
    public String updateProduct(@PathVariable int id, Model model) {
        model.addAttribute("products", productService.findById(id));
        model.addAttribute("category", categoryService.getAllCategory());
        return "editProduct";
    }

    @PostMapping("/saveProduct")
    public String upProduct(@ModelAttribute Product product) {
        productService.save(product.getId(), product);
        return "redirect:/admin";
    }




}
