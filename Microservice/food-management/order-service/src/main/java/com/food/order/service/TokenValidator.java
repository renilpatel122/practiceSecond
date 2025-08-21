package com.food.order.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TokenValidator {

    private final WebClient webClient;

    public TokenValidator(WebClient.Builder builder, @Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = builder.baseUrl(authServiceUrl).build();
    }

    public boolean isCustomer(String token) {
        try {
            return webClient.get()
                    .uri("/validate/customer")
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (Exception e) {
            return false;
        }
    }
}
