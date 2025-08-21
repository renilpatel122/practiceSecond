package com.assessment.order_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemDTO {
    private Long id;
    private Long menuItemId;
    private int availableQuantity;
}
