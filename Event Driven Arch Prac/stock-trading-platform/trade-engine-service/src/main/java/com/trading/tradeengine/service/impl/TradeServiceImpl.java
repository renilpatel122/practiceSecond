package com.trading.tradeengine.service.impl;

import com.trading.common.constants.KafkaTopics;
import com.trading.common.event.OrderPlacedEvent;
import com.trading.common.event.TradeExecutedEvent;
import com.trading.common.exception.ResourceNotFoundException;
import com.trading.tradeengine.entity.Trade;
import com.trading.tradeengine.repository.TradeRepository;
import com.trading.tradeengine.service.OrderBook;
import com.trading.tradeengine.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;
    private final KafkaTemplate<String, TradeExecutedEvent> kafkaTemplate;
    private final OrderBook orderBook;

    @Override
    public void processOrderPlacedEvent(OrderPlacedEvent orderPlacedEvent) {
        log.info("Processing OrderPlacedEvent: {}", orderPlacedEvent);

        Optional<TradeExecutedEvent> matchedTrade = orderBook.match(orderPlacedEvent);

        // Dummy: Create matched trade (buy and sell orders matched)
        // For demo, we assume this order matched with a dummy counter order
        Trade trade = Trade.builder()
                .buyOrderId(orderPlacedEvent.getOrderId()) // assuming buy order for demo
                .sellOrderId(String.valueOf(UUID.randomUUID())) // dummy sell order id
                .stockSymbol(orderPlacedEvent.getStockSymbol())
                .quantity(orderPlacedEvent.getQuantity())
                .price(orderPlacedEvent.getPrice())
                .timestamp(Instant.now())
                .build();

        tradeRepository.save(trade);
        log.info("Saved trade to DB: {}", trade);

        // Publish TradeExecuted event
        TradeExecutedEvent tradeExecutedEvent = TradeExecutedEvent.builder()
                .tradeId(trade.getTradeId())
                .buyOrderId(trade.getBuyOrderId())
                .sellOrderId(trade.getSellOrderId())
                .stockSymbol(trade.getStockSymbol())
                .quantity(trade.getQuantity())
                .price(trade.getPrice())
                .timestamp(trade.getTimestamp().toString())
                .build();

        kafkaTemplate.send(KafkaTopics.TRADE_EXECUTED, tradeExecutedEvent.getTradeId(), tradeExecutedEvent).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to publish TradeExecuted event", ex);
            } else {
                log.info("TradeExecuted event published successfully: {}", tradeExecutedEvent);
            }
        });
    }

//    @Override
//    public void processOrderPlacedEvent(OrderPlacedEvent orderPlacedEvent) {
//        log.info("Processing OrderPlacedEvent: {}", orderPlacedEvent);
//
//        Optional<TradeExecutedEvent> matchedTrade = orderBook.match(orderPlacedEvent);
//
//        if (matchedTrade.isPresent()) {
//            TradeExecutedEvent tradeEvent = matchedTrade.get();
//
//            Trade trade = Trade.builder()
//                    .tradeId(tradeEvent.getTradeId())
//                    .buyOrderId(tradeEvent.getBuyOrderId())
//                    .sellOrderId(tradeEvent.getSellOrderId())
//                    .stockSymbol(tradeEvent.getStockSymbol())
//                    .quantity(tradeEvent.getQuantity())
//                    .price(tradeEvent.getPrice())
//                    .timestamp(Instant.parse(tradeEvent.getTimestamp()))
//                    .build();
//
//            tradeRepository.save(trade);
//            log.info("Saved trade to DB: {}", trade);
//
//            kafkaTemplate.send(KafkaTopics.TRADE_EXECUTED, trade.getTradeId(), tradeEvent)
//                    .whenComplete((result, ex) -> {
//                        if (ex != null) {
//                            log.error("Failed to publish TradeExecuted event", ex);
//                        } else {
//                            log.info("TradeExecuted event published successfully: {}", tradeEvent);
//                        }
//                    });
//        } else {
//            log.info("No match found, order queued in order book.");
//        }
//    }


    @Override
    public List<Trade> getAllTrade() {
        List<Trade> tradeList = tradeRepository.findAll();
        if (tradeList.isEmpty()) {
            throw new ResourceNotFoundException("No trade found");
        }
        return tradeList;
    }

    @Override
    public Optional<Trade> getTradeById(String tradeId) {
        return tradeRepository.findById(tradeId).or(() -> {
            throw new ResourceNotFoundException("Trade not found with id: "+ tradeId);
        });
    }
}
