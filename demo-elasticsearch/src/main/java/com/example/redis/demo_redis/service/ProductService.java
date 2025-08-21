package com.example.redis.demo_redis.service;

import com.example.redis.demo_redis.model.Product;

import java.util.List;

public interface ProductService {
     Product saveProduct(Product product);

     List<Product> search(String query);

     Product getById(String id);

}
