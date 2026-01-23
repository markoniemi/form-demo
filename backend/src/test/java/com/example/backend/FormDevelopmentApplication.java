package com.example.backend;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * LocalDevelopmentApplication
 * Entry point for local development with in-memory OAuth server and H2 database.
 */
public class FormDevelopmentApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BackendApplication.class)
            .profiles("local")
            .run(args);
    }
}
