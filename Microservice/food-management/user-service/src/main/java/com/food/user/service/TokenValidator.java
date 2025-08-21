package com.food.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    private final WebClient.Builder webClientBuilder;

    @Value("${auth.service.url}")
    private String authUrl;

    public boolean validateToken(String token) {
        Boolean isValid = webClientBuilder.build()
                .get()
                .uri(authUrl + "/validate" + "?token=" + token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return Boolean.TRUE.equals(isValid);
    }
}
