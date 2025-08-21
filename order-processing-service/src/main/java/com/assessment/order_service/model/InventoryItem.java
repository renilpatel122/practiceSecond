package com.assessment.order_service.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private MenuItem menuItem;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

}

