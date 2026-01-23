package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;



@SpringBootTest
@Testcontainers
public class OAuthIntegrationTest {
    @Container
    public static GenericContainer<?> oauthServer = new GenericContainer<>(DockerImageName.parse("form-demo/oauth:latest"))
        .withExposedPorts(8080).withImagePullPolicy(PullPolicy.defaultPolicy());

    @DynamicPropertySource
    static void oauthProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri",
            () -> "http://" + oauthServer.getHost() + ":" + oauthServer.getMappedPort(8080));
        registry.add("spring.datasource.url", () -> "jdbc:h2:mem:testdb");
        registry.add("spring.datasource.driverClassName", () -> "org.h2.Driver");
        registry.add("spring.datasource.username", () -> "sa");
        registry.add("spring.datasource.password", () -> "");
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.H2Dialect");
    }

    @Test
    void testOAuthServerIsRunning() {
        assert oauthServer.isRunning();
    }
}
