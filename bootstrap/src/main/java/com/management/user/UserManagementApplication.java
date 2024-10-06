package com.management.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(scanBasePackages = "com.management.user")
public class UserManagementApplication {
    private static final Logger logger = LoggerFactory.getLogger(UserManagementApplication.class);
    public static void main(String[] args){
        logger.info("Application is starting...");
        SpringApplication.run(UserManagementApplication.class, args);
    }
}