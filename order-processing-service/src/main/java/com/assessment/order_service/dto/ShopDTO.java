package com.assessment.order_service.dto;

import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopDTO {
    private Long id;
    private String name;
    private String location;
    private String contactNumber;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int numberOfQueues;
    private int queueMaxSize;
}
