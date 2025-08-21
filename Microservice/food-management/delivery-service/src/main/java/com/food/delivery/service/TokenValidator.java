package com.food.delivery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TokenValidator {

    private final WebClient webClient;

    public TokenValidator(WebClient.Builder builder, @Value("${auth.service.url}") String authUrl) {
        this.webClient = builder.baseUrl(authUrl).build();
    }

    public boolean isAdmin(String token) {
        try {
            return webClient.get()
                    .uri("/validate/admin")
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (Exception e) {
            return false;
        }
    }
}
