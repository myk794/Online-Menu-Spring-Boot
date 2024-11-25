package com.yigitkaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.yigitkaya"})
@ComponentScan(basePackages = {"com.yigitkaya"})
@EnableJpaRepositories(basePackages = {"com.yigitkaya"})
@SpringBootApplication
public class OnlineMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineMenuApplication.class, args);
	}

}
