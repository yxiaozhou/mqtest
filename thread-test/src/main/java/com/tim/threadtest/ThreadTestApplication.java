package com.tim.threadtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ThreadTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadTestApplication.class,args);
    }
}
