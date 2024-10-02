package com.daviag.bookshop.catalog;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;

import java.time.Duration;

//@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

//    @Bean
    KeycloakContainer keycloakContainer(DynamicPropertyRegistry registry) {
        var keycloak = new KeycloakContainer("quay.io/keycloak/keycloak:25.0")
                .withRealmImportFile("test-realm-config.json")
//                .withStartupTimeout(Duration.ofMinutes(5))
                ;
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri",
                () -> keycloak.getAuthServerUrl() + "/realms/Bookshop");
        return keycloak;
    }
}
