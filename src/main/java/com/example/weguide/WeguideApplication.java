package com.example.weguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.weguide.entity")
@ComponentScan(basePackages = "com.example.weguide")
public class WeguideApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeguideApplication.class, args);
	}

}
