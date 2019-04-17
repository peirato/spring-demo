package com.peirato.dome;

import com.peirato.dome.redis.SpringRedisPoolSettings;
import com.peirato.dome.redis.SpringRedisSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({ SpringRedisPoolSettings.class, SpringRedisSettings.class })
public class RedisDomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisDomeApplication.class, args);
	}

}
