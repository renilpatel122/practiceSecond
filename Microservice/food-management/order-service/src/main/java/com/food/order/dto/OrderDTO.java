package com.food.order.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private String status;
    private LocalDateTime orderTime;
    private List<OrderItemDTO> items;
}
