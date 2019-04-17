package com.tim.producer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Bean
    public Queue myQueue() {
        Queue queue = new Queue("myQueue1");
        return queue;
    }

    @Bean
    public Queue myQueue1() {
        Queue queue = new Queue("myQueue2");
        return queue;
    }
    @Bean
    public Queue myQueue2() {
        Queue queue = new Queue("myQueue3");
        return queue;
    }
    @Bean
    public Queue myQueue3() {
        Queue queue = new Queue("myQueue4");
        return queue;
    }
}
