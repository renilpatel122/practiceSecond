package com.trading.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    private String userId;

    private String stockSymbol;

    private int quantity;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private Instant timestamp;

    public Order(String userId, String stockSymbol, int quantity, BigDecimal price, OrderType orderType) {
        this.userId = userId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }

    public enum OrderType {
        BUY, SELL
    }
}
