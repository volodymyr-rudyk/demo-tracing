package org.example.demotracing.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class Config {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .setConnectTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
    }
}
