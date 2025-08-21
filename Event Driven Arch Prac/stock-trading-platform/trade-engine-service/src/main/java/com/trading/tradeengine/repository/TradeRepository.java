package com.trading.tradeengine.repository;

import com.trading.tradeengine.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TradeRepository extends JpaRepository<Trade, String> {
}
