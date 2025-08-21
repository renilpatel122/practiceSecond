package com.food.order.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private String itemName;
    private double price;
    private int quantity;
}
