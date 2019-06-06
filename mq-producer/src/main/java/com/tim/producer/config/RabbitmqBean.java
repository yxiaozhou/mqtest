package com.tim.producer.config;

import com.tim.producer.ConfirmCallback.ConfirmCallback1;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitmqBean {
    @Autowired
    public Rabbitmq rabbitmq;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitmq.getHost());
        connectionFactory.setPort(rabbitmq.getPort());
        connectionFactory.setVirtualHost(rabbitmq.getVirtualHost());
        connectionFactory.setUsername(rabbitmq.getUsername());
        connectionFactory.setPassword(rabbitmq.getPassword());
        return connectionFactory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate() {
        rabbitTemplate.setConfirmCallback(new ConfirmCallback1());
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
