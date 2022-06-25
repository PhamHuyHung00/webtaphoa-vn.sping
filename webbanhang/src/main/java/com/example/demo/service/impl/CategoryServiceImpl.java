package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findNameCateById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void createCate(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }
}
