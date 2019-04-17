package com.tim.consumer.reciver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component

public class TopicListener {

    @RabbitListener(queues = "topic.message") // 监听器监听指定的queue
    public void process1(Message message, Channel channel){
        System.out.println("我是监听topic.message的 :  "+message.getBody());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues="topic.messages") // 监听器监听指定的queue
    public void process2(Message message, Channel channel){
        System.out.println("我是监听topic.messages的 :  "+message.getBody());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

