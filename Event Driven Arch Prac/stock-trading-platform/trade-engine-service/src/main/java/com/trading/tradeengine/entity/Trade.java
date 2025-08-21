package com.trading.tradeengine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "trades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {

    @Id
    @Column(name = "trade_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tradeId;

    @Column(name = "buy_order_id", nullable = false)
    private String buyOrderId;

    @Column(name = "sell_order_id", nullable = false)
    private String sellOrderId;

    @Column(name = "stock_symbol", nullable = false)
    private String stockSymbol;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;
}

