package com.food.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth/**")
                        .uri("http://localhost:8081"))
                .route("user-service", r -> r.path("/users/**")
                        .uri("http://localhost:8082"))
                .route("restaurant-service", r -> r.path("/restaurants/**")
                        .uri("http://localhost:8083"))
                .route("order-service", r -> r.path("/orders/**")
                        .uri("http://localhost:8084"))
                .route("delivery-service", r -> r.path("/delivery/**")
                        .uri("http://localhost:8086"))
                .route("notification-service", r -> r.path("/notifications/**")
                        .uri("http://localhost:8085"))
                .build();
    }
}
