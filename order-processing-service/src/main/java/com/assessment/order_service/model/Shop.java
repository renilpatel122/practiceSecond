package com.assessment.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name = "shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String contactNumber;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int numberOfQueues;
    private int queueMaxSize;

    @OneToMany(mappedBy = "shop")
    private List<MenuItem> menuItems;
}

