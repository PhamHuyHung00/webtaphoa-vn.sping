package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getListCate(Model model) {
        model.addAttribute("categoryList", categoryService.getAllCategory());
        return "category";
    }

    @GetMapping("/deleteCate/{id}")
    public String deleteCate(@PathVariable int id) {
        categoryService.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("/add")
    public String showAddCate(Model model) {
        model.addAttribute("CateForm", new Category());
        return "add-category";
    }

    @PostMapping("/add")
    public String addCate(Category category) {
        categoryService.createCate(category);
        return "redirect:/category";
    }
}
