package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategory();

//    Category findNameCateById(int id);

    void deleteById(int id);

    void createCate(Category category);

//    Category findById(int id);
}
