package com.example.redis.demo_redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// LocationDTO.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private double lat;
    private double lng;
}
