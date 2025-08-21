package com.trading.marketdata.service;

import com.trading.marketdata.dto.StockUpdatedEvent;
import com.trading.marketdata.producer.StockUpdatePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketDataService {

    private final StockUpdatePublisher publisher;
    private final StringRedisTemplate redisTemplate;
    private final Random random = new Random();

    private final List<String> STOCKS = List.of("AAPL", "GOOG", "TSLA", "MSFT");

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void publishMockPrices() {
        for (String symbol : STOCKS) {
            BigDecimal price = generateRandomPrice();
            String timestamp = Instant.now().toString();

            // Store to Redis
            redisTemplate.opsForValue().set("stock:" + symbol, price.toPlainString());

            // Build and publish event
            StockUpdatedEvent event = StockUpdatedEvent.builder()
                    .stockSymbol(symbol)
                    .price(price)
                    .timestamp(timestamp)
                    .build();

            publisher.publish(event);
        }
    }

    private BigDecimal generateRandomPrice() {
        double price = 100 + (500 - 100) * random.nextDouble(); // 100 to 500
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }
}
