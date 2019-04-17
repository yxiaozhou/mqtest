package com.tim.consumer.reciver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DelayListener {

    @RabbitListener(queues = "delayQueue")
    public void process(Message message, Channel channel) {
//        System.out.println("Receive1:" + message);
        System.out.println("Receive1 over"+System.currentTimeMillis());
        try {
            System.out.println(message.getMessageProperties().getDeliveryTag());
            //消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//            //ack返回false，并重新回到队列，api里面解释得很清楚
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            //拒绝消息
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @RabbitListener(queues = "myQueue1")
//    public void process2(Message message, Channel channel) {
//        System.out.println("Receive2:" + message);
//        try {
//            System.out.println(message.getMessageProperties().getDeliveryTag());
//            //消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
////            //ack返回false，并重新回到队列，api里面解释得很清楚
////            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
////            //拒绝消息
////            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

