package com.example.redis.demo_redis.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Entity
@Table(name = "products")
@Document(indexName = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private double price;
}

