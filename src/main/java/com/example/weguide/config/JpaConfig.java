package com.example.weguide.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.example.weguide.entity")
public class JpaConfig {
    // 다른 JPA 설정
}
