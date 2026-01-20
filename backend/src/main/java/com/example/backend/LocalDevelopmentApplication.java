package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LocalDevelopmentApplication
 * Entry point for local development with in-memory OAuth server and H2 database.
 */
@SpringBootApplication
public class LocalDevelopmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocalDevelopmentApplication.class, args);
        System.out.println("LocalDevelopmentApplication started with H2 and in-memory OAuth server (placeholder)");
    }
}
