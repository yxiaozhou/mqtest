package com.tim.ordertest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class TestConfig2 {

    @Qualifier("baseConfig1")
    @Autowired
    public BaseConfig baseConfig1;
    @Autowired
    public BaseConfig baseConfig2;
    
    @Bean
    public BaseConfig baseConfig1(){
        System.out.println("======1=====");
        return new BaseConfig(1);
    }

    @Bean
    public BaseConfig baseConfig2(BaseConfig baseConfig1){
        System.out.println("======2=====");
        return new BaseConfig(baseConfig1.getId());
    }
}
