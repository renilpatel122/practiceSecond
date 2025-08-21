package com.trading.marketdata.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StockUpdatedEvent {
    private String stockSymbol;
    private BigDecimal price;
    private String timestamp;
}

