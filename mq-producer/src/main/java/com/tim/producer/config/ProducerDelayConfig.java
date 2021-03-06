package com.tim.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class ProducerDelayConfig {

    @Bean
    public Queue myQueue() {
        Queue queue = new Queue("delayQueue",true);
        return queue;
    }

    @Bean
    public CustomExchange delayExchange(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delayExchange","x-delayed-message",true,false,args);
    }
    @Bean
    public Binding bindingNotify() {
        return BindingBuilder.bind(myQueue()).to(delayExchange()).with("delayQueue").noargs();
    }
}
