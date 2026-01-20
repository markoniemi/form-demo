package com.example.backend;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.GenericContainer;

@Testcontainers
public class OAuthIntegrationTest {
    @Container
    public GenericContainer<?> oauthServer = new GenericContainer<>("form-demo/oauth:latest")
        .withExposedPorts(8080);

    @Test
    void testOAuthServerIsRunning() {
        assert oauthServer.isRunning();
    }
}
