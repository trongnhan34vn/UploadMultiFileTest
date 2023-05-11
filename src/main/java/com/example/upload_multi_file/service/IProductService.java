package com.example.upload_multi_file.service;

import com.example.upload_multi_file.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
}
