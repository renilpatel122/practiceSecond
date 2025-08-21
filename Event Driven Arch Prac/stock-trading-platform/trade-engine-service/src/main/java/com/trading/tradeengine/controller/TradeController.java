package com.trading.tradeengine.controller;

import com.trading.tradeengine.entity.Trade;
import com.trading.tradeengine.repository.TradeRepository;
import com.trading.tradeengine.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @GetMapping
    public ResponseEntity<List<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradeService.getAllTrade());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable("id") String id) {
        return tradeService.getTradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
