package com.food.delivery.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryStatusUpdateDTO {
    private Long deliveryId;
    private String newStatus;
}
