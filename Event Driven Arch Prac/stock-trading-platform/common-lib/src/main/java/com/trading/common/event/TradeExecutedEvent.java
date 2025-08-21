package com.trading.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeExecutedEvent {
    private String tradeId;
    private String buyOrderId;
    private String sellOrderId;
    private String stockSymbol;
    private int quantity;
    private BigDecimal price;
    private String timestamp;
}

