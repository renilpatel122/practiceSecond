package com.example.redis.demo_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "com.example.redis.demo_redis.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.example.redis.demo_redis.repository.search")
public class DemoElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoElasticSearchApplication.class, args);
	}

}
