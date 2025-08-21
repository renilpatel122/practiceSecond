package com.example.redis.demo_redis.controller;

import com.example.redis.demo_redis.model.Product;
import com.example.redis.demo_redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000") // React app ni origin
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product p) {
        return ResponseEntity.ok(service.saveProduct(p));
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String query) {
        return service.search(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<String>> getSuggestions(@RequestParam("query") String query) {
        List<Product> products = service.search(query);
        List<String> suggestions = products.stream()
                .map(Product::getName)
                .distinct()
                .limit(5)
                .toList();

        return ResponseEntity.ok(suggestions);
    }


}
