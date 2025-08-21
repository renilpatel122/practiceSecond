package com.example.redis.demo_redis.repository.jpa;

import com.example.redis.demo_redis.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}


