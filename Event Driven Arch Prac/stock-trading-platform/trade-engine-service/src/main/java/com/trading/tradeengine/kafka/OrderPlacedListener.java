package com.trading.tradeengine.kafka;

import com.trading.common.event.OrderPlacedEvent;
import com.trading.tradeengine.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderPlacedListener {

    private final TradeService tradeService;

    @KafkaListener(topics = "order-placed", groupId = "trade-engine-group")
    public void listenOrderPlaced(OrderPlacedEvent event) {
        log.info("Received OrderPlacedEvent: {}", event);
        tradeService.processOrderPlacedEvent(event);
    }
}
