package com.tim.consumer.config;

import com.tim.consumer.reciver.WorkReceiver;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerMultipleConfig {

    @Bean
    public Queue workQueue() {
        return new Queue("work-queue");
    }
    @Bean
    public WorkReceiver workReceiver() {
        return new WorkReceiver("Receiver0");
    }

    @Bean
    public WorkReceiver workReceiver1() {
        return new WorkReceiver("Receiver1");
    }
}
