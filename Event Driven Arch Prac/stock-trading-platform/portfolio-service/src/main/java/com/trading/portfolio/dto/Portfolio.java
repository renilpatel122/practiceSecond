package com.trading.portfolio.dto;

import com.trading.portfolio.entity.Holding;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Portfolio {

    private String userId;
    private List<Holding> holdings;
    private BigDecimal pnl;
}
