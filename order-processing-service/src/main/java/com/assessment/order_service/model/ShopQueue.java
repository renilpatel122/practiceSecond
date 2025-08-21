package com.assessment.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shop_queue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Customer customer;

    private int queueNumber;
    private String status; // WAITING, SERVED, SKIPPED
}
