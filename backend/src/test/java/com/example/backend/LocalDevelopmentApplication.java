package com.example.backend;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * LocalDevelopmentApplication
 * Entry point for local development with in-memory OAuth server and H2 database.
 */
public class LocalDevelopmentApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BackendApplication.class)
            .profiles("local")
            .run(args);
        System.out.println("LocalDevelopmentApplication started with H2 and in-memory OAuth server (placeholder)");
    }
}
