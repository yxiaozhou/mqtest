package com.tim.ordertest.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//@Configuration
@Order(2)
@ConditionalOnBean(name = "createRunnable2")
public class TestConfig {

    @Bean
    public Runnable createRunnable1(){
        System.out.println("======1=====");
        return () -> {};
    }

}
