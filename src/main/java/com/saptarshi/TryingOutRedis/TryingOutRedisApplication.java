package com.saptarshi.TryingOutRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TryingOutRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryingOutRedisApplication.class, args);
	}

}
