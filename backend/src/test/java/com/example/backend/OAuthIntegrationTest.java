package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@Testcontainers
public class OAuthIntegrationTest {
    @Container
    public static GenericContainer<?> oauthServer = new GenericContainer<>(DockerImageName.parse("form-demo/oauth:latest"))
        .withExposedPorts(8080);
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @DynamicPropertySource
    static void oauthProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri",
            () -> "http://" + oauthServer.getHost() + ":" + oauthServer.getMappedPort(8080));
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void testOAuthServerIsRunning() {
        assert oauthServer.isRunning();
    }
}
