package com.trading.portfolio.service;

import com.trading.common.event.TradeExecutedEvent;
import com.trading.portfolio.dto.Portfolio;

public interface PortfolioService {
    void processTradeExecuted(TradeExecutedEvent event);
    Portfolio getPortfolioByUserId(String userId);
}
