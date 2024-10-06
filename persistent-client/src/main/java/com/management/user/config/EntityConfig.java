package com.management.user.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@EntityScan(basePackages = "com.management.user.entity")
public class EntityConfig {
}

