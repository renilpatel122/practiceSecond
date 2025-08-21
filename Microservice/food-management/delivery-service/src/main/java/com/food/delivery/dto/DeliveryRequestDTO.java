package com.food.delivery.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryRequestDTO {
    private Long orderId;
    private Long deliveryPersonId;
}
