package com.trading.portfolio.entity;

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

@Entity
@Table(name = "holding")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String stockSymbol;

    private int quantity;          // positive number of shares held
    private BigDecimal avgPrice;   // average purchase price per share

    public Holding(String userId, String stockSymbol, int quantity, BigDecimal avgPrice) {
        this.userId = userId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.avgPrice = avgPrice;
    }
}
