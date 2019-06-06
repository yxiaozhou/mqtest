package com.tim.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.rabbitmq")
    public Rabbitmq rabbitmq() {
        return new Rabbitmq();
    }
}
