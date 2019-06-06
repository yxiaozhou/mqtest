package com.tim.ordertest.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//@Configuration
@Order(1)
public class TestConfig1 {

    @Bean
    public Runnable createRunnable2(){
        System.out.println("======2=====");
        return () -> {};
    }

}
