package com.assessment.order_service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private Long customerId;
    private Long shopId;
    private int queueNumber;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> orderItems;
}
