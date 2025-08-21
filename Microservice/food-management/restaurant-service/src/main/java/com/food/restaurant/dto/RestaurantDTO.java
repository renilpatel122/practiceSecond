package com.food.restaurant.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDTO {
    private Long id;
    private String name;
    private String location;
    private String cuisine;
    private Boolean active;
}
