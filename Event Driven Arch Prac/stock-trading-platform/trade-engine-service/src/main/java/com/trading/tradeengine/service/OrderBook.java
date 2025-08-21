package com.trading.tradeengine.service;

import com.trading.common.event.OrderPlacedEvent;
import com.trading.common.event.TradeExecutedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderBook {

    private final Map<String, PriorityQueue<OrderPlacedEvent>> buyOrders = new ConcurrentHashMap<>();
    private final Map<String, PriorityQueue<OrderPlacedEvent>> sellOrders = new ConcurrentHashMap<>();

    public Optional<TradeExecutedEvent> match(OrderPlacedEvent newOrder) {
        String symbol = newOrder.getStockSymbol();
        if (newOrder.getOrderType().equalsIgnoreCase("BUY")) {
            return matchBuyOrder(newOrder, symbol);
        } else {
            return matchSellOrder(newOrder, symbol);
        }
    }

    private Optional<TradeExecutedEvent> matchBuyOrder(OrderPlacedEvent buy, String symbol) {
        PriorityQueue<OrderPlacedEvent> sells = sellOrders.computeIfAbsent(symbol,
                k -> new PriorityQueue<>(Comparator.comparing(OrderPlacedEvent::getPrice)));

        while (!sells.isEmpty() && buy.getPrice().compareTo(sells.peek().getPrice()) >= 0) {
            OrderPlacedEvent sell = sells.poll();
            int quantity = Math.min(buy.getQuantity(), sell.getQuantity());

            TradeExecutedEvent trade = buildTrade(buy, sell, quantity, sell.getPrice());
            return Optional.of(trade);
        }

        buyOrders.computeIfAbsent(symbol, k -> new PriorityQueue<>(Comparator.comparing(OrderPlacedEvent::getPrice).reversed()))
                .add(buy);
        return Optional.empty();
    }

    private Optional<TradeExecutedEvent> matchSellOrder(OrderPlacedEvent sell, String symbol) {
        PriorityQueue<OrderPlacedEvent> buys = buyOrders.computeIfAbsent(symbol,
                k -> new PriorityQueue<>(Comparator.comparing(OrderPlacedEvent::getPrice).reversed()));

        while (!buys.isEmpty() && buys.peek().getPrice().compareTo(sell.getPrice()) >= 0) {
            OrderPlacedEvent buy = buys.poll();
            int quantity = Math.min(buy.getQuantity(), sell.getQuantity());

            TradeExecutedEvent trade = buildTrade(buy, sell, quantity, buy.getPrice());
            return Optional.of(trade);
        }

        sellOrders.computeIfAbsent(symbol, k -> new PriorityQueue<>(Comparator.comparing(OrderPlacedEvent::getPrice)))
                .add(sell);
        return Optional.empty();
    }

    private TradeExecutedEvent buildTrade(OrderPlacedEvent buy, OrderPlacedEvent sell, int quantity, BigDecimal price) {
        return TradeExecutedEvent.builder()
                .tradeId(UUID.randomUUID().toString())
                .buyOrderId(buy.getOrderId())
                .sellOrderId(sell.getOrderId())
                .stockSymbol(buy.getStockSymbol())
                .quantity(quantity)
                .price(price)
                .timestamp(Instant.now().toString())
                .build();
    }
}