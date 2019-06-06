package com.tim.producer.ConfirmCallback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component
public class ConfirmCallback1 implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("confirm: " + correlationData.getId() + ",s=" + s + ",b:" + b);
    }
}
