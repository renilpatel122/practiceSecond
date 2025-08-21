package com.assessment.order_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopQueueDTO {
    private Long id;
    private Long shopId;
    private Long customerId;
    private int queueNumber;
    private String status; // WAITING, SERVED, SKIPPED
}
