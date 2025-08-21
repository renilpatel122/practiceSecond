package com.example.inventory.controller;

import com.example.api.controller.ProductsApi;
import com.example.api.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController implements ProductsApi {

    @Override
    public ResponseEntity<List<Product>> getProducts() {
        Product product1 = new Product().id(1L).name("Laptop").price(1200.50F);
        Product product2 = new Product().id(2L).name("Keyboard").price(150.75F);
        return ResponseEntity.ok(Arrays.asList(product1, product2));
    }
}
