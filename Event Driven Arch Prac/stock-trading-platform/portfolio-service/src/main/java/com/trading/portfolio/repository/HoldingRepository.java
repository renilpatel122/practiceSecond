package com.trading.portfolio.repository;

import com.trading.portfolio.entity.Holding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoldingRepository extends JpaRepository<Holding, Long> {
    List<Holding> findByUserId(String userId);
    Optional<Holding> findByUserIdAndStockSymbol(String userId, String stockSymbol);
}
