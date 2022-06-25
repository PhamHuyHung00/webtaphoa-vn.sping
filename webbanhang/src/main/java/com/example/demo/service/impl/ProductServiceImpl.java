package com.example.demo.service.impl;


import com.example.demo.dto.ProductRequest;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;


    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }


    @Override
    public Product addProduct(ProductRequest productRequest) {
        Product product2Add = modelMapper.map(productRequest, Product.class);
        String logoPath = storageService.saveFile(productRequest.getLogo());
        product2Add.setLogoPath(logoPath);
        productRepository.save(product2Add);
        return product2Add;
    }


    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void save(int id, Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(int id) {
//        productRepository.deleteById(id);
        productRepository.deleteProductById(id);
    }

    public List<Product> getProductByLike(String name) {
        return productRepository.searchProduct(name);
    }

    public List<Product> getProducts() {
        return productRepository.getProduct();
    }

    public List<Product> getProductByCateId(int id) {
        return productRepository.getProductByCateId(id);
    }

    public Product detailProduct(int id) {
        return productRepository.getDetailProduct(id);
    }

    //Phân loại sản phẩm theo giá


    public List<Product> getProductByPriceOrderBy( ) {
        return productRepository.getProductByPriceOrderBy();
    }

    public List<Product> getProductByPriceOrderByDesc() {
        return productRepository.getProductByPriceOrderByDesc();
    }

    public List<Product> getProductByPriceOrderByDescCategory(int id) {
        return productRepository.getProductByPriceOrderByDescCategory(id);
    }

    public List<Product> getProductByPriceOrderByCategory(int id) {
        return productRepository.getProductByPriceOrderByCategory(id);
    }


}
