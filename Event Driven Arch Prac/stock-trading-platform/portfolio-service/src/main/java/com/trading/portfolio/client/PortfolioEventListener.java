package com.trading.portfolio.client;

import com.trading.common.constants.KafkaTopics;
import com.trading.common.event.TradeExecutedEvent;
import com.trading.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioEventListener {

    private final PortfolioService portfolioService;

    @KafkaListener(topics = KafkaTopics.TRADE_EXECUTED, groupId = "portfolio-service")
    public void onTradeExecuted(TradeExecutedEvent event) {
        portfolioService.processTradeExecuted(event);
    }
}

