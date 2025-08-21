package com.trading.tradeengine.service;

import com.trading.common.event.OrderPlacedEvent;
import com.trading.tradeengine.entity.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeService {
    void processOrderPlacedEvent(OrderPlacedEvent orderPlacedEvent);
    List<Trade> getAllTrade();
    Optional<Trade> getTradeById(String tradeId);
}
