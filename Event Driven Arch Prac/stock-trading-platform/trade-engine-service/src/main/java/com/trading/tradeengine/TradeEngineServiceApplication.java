package com.trading.tradeengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.trading.tradeengine", "com.trading.common"})
public class TradeEngineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeEngineServiceApplication.class, args);
	}
}
