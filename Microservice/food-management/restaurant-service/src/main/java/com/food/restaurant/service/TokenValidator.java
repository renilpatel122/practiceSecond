package com.food.restaurant.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TokenValidator {

    private final WebClient webClient;

    public TokenValidator(WebClient.Builder webClientBuilder, @Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
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
