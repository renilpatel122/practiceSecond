package com.trading.marketdata.producer;

import com.trading.common.constants.KafkaTopics;
import com.trading.marketdata.dto.StockUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockUpdatePublisher {

    private final KafkaTemplate<String, StockUpdatedEvent> kafkaTemplate;

    public void publish(StockUpdatedEvent event) {
        kafkaTemplate.send(KafkaTopics.STOCK_UPDATED, event.getStockSymbol(), event).whenComplete((res, ex) -> {
            if (ex != null) {
                log.error("Failed to publish StockUpdatedEvent", ex);
            } else {
                log.info("Published StockUpdatedEvent: {}", event);
            }
        });
    }
}