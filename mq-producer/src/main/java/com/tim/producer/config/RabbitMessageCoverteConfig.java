package com.tim.producer.config;

import com.tim.producer.ConfirmCallback.ConfirmCallback1;
import com.tim.producer.sender.Sender1;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RabbitMessageCoverteConfig {


}
