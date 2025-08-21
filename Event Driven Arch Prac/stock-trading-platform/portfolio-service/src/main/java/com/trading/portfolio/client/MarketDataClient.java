package com.trading.portfolio.client;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class MarketDataClient {

    private final StringRedisTemplate redisTemplate;

    public BigDecimal getCurrentPrice(String stockSymbol) {
        String key = "stock:" + stockSymbol.toUpperCase();
        String priceStr = redisTemplate.opsForValue().get(key);

        if (priceStr == null) {
            throw new RuntimeException("Price not found in Redis for symbol: " + stockSymbol);
        }
        return new BigDecimal(priceStr);
    }
}