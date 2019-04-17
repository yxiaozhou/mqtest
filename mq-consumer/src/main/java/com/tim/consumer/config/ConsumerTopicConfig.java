package com.tim.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerTopicConfig {

    //  a. 配置队列queue
    @Bean("message")
    public Queue myQueue1() {
        return new Queue("topic.message");
    }
    @Bean("messages")
    public Queue myQueue2() {
        return new Queue("topic.messages");
    }
    //  b. 配置交换机Exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }
    //  c. 将队列按照相应的规则绑定到交换机上
    @Bean
    public Binding bindingExchangeMessage(@Qualifier("message")Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    public Binding bindingExchangeMessages(@Qualifier("messages")Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
