package com.trading.orderservice.dto;

import com.trading.orderservice.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRequest {
    private String userId;
    private String stockSymbol;
    private int quantity;
    private BigDecimal price;
    private Order.OrderType orderType;
}

