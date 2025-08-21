package com.food.delivery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long deliveryPersonId;
    private String status; // ASSIGNED, OUT_FOR_DELIVERY, DELIVERED
    private LocalDateTime assignedTime;
    private LocalDateTime deliveredTime;
}
