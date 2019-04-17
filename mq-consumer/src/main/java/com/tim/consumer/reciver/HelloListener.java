package com.tim.consumer.reciver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.tim.consumer.domain.TestData;
import com.tim.consumer.service.TestService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HelloListener {

    @Autowired
    private TestService testService;
    //    @RabbitListener(queues ={"myQueue1"} ,concurrency = "10")
    //    @RabbitListener(queues ={"myQueue1","myQueue"} ,concurrency = "10")
    @RabbitListener(queues ={"myQueue1"} ,concurrency = "10",containerFactory = "rabbitListenerContainerFactory2")
    public void process(Message message, Channel channel) {
        try {
            TestData t = new TestData();
            t.setMsg(new String(message.getBody()));
            t.setRemark(channel.getChannelNumber()+"");
            testService.saveData(t);
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

