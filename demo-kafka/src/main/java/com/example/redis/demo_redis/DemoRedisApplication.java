package com.example.redis.demo_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class DemoRedisApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DemoRedisApplication.class, args);
	}

}
