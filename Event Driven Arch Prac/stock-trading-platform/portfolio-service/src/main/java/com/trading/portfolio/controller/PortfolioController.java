package com.trading.portfolio.controller;

import com.trading.portfolio.dto.Portfolio;
import com.trading.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable("userId") String userId) {
        Portfolio portfolio = portfolioService.getPortfolioByUserId(userId);
        return ResponseEntity.ok(portfolio);
    }
}
