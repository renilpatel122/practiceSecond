package com.trading.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPlacedEvent {
    private String orderId;
    private String userId;
    private String stockSymbol;
    private int quantity;
    private BigDecimal price;
    private String orderType;
    private String timestamp;
}


