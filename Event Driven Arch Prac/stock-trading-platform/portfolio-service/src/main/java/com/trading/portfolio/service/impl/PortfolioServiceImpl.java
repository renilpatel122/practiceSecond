package com.trading.portfolio.service.impl;

import com.trading.common.event.TradeExecutedEvent;
import com.trading.portfolio.client.MarketDataClient;
import com.trading.portfolio.dto.Portfolio;
import com.trading.portfolio.entity.Holding;
import com.trading.portfolio.repository.HoldingRepository;
import com.trading.portfolio.service.PortfolioService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final HoldingRepository holdingRepository;
    private final MarketDataClient marketDataClient;
    private final WebClient webClient;

    /**
     * Update portfolio holdings based on TradeExecuted event.
     */
    @Transactional
    public void processTradeExecuted(TradeExecutedEvent event) {
        // Buyer portfolio: increase quantity and update avgPrice
        updateHolding(event.getBuyOrderId(), event.getStockSymbol(), event.getQuantity(), event.getPrice(), true);

        // Seller portfolio: decrease quantity (and possibly avgPrice unchanged)
        updateHolding(event.getSellOrderId(), event.getStockSymbol(), event.getQuantity(), event.getPrice(), false);
    }

    private void updateHolding(String orderId, String stockSymbol, int quantity, BigDecimal price, boolean isBuy) {
        // You need to map orderId -> userId (e.g. call Order Service or maintain cache)
        String userId = getUserIdByOrderId(orderId);

        Holding holding = holdingRepository.findByUserIdAndStockSymbol(userId, stockSymbol)
                .orElse(new Holding(userId, stockSymbol, 0, BigDecimal.ZERO));

        if (isBuy) {
            // Recalculate avg price and add quantity
            int totalQuantity = holding.getQuantity() + quantity;
            BigDecimal totalCost = holding.getAvgPrice().multiply(BigDecimal.valueOf(holding.getQuantity()))
                    .add(price.multiply(BigDecimal.valueOf(quantity)));

            BigDecimal newAvgPrice = totalQuantity == 0 ? BigDecimal.ZERO : totalCost.divide(BigDecimal.valueOf(totalQuantity), RoundingMode.HALF_UP);

            holding.setQuantity(totalQuantity);
            holding.setAvgPrice(newAvgPrice);
        } else {
            // Sell: reduce quantity
            int newQuantity = holding.getQuantity() - quantity;
            if (newQuantity < 0) {
                throw new IllegalStateException("Selling more shares than held");
            }
            holding.setQuantity(newQuantity);
            // avgPrice unchanged on sell
        }

        holdingRepository.save(holding);
    }

    /**
     * Fetch portfolio details including P&L for a user.
     */
    public Portfolio getPortfolioByUserId(String userId) {
        List<Holding> holdings = holdingRepository.findByUserId(userId);

        BigDecimal pnl = BigDecimal.ZERO;
        for (Holding h : holdings) {
            BigDecimal currentPrice = marketDataClient.getCurrentPrice(h.getStockSymbol());
            BigDecimal profit = currentPrice.subtract(h.getAvgPrice()).multiply(BigDecimal.valueOf(h.getQuantity()));
            pnl = pnl.add(profit);
        }

        return new Portfolio(userId, holdings, pnl);
    }

    public String getUserIdByOrderId(String orderId) {
        Map<String, Object> response = webClient.get()
                .uri("http://loclahost:8081/api/orders/{orderId}", orderId)
                .retrieve()
                .bodyToMono(Map.class)
                .block(); // Use Mono<Map<String, Object>> if reactive

        if (response != null && response.containsKey("userId")) {
            return response.get("userId").toString();
        }
        return null;
    }

}
