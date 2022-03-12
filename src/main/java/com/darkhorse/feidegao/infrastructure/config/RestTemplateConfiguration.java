package com.darkhorse.feidegao.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfiguration {
    private final RestTemplateInterceptor restTemplateInterceptor;

    public RestTemplateConfiguration() {
        this.restTemplateInterceptor = new RestTemplateInterceptor();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(restTemplateInterceptor));
        return restTemplate;
    }
}