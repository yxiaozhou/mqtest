package com.tim.producer.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.producer.domain.User;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Sender1 {

    @Autowired
    private AmqpTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendHello(int i) {
        User u = new User();
        u.setId(i+"");
        u.setName(i+"_123");
        template.convertAndSend("myQueue1", u);
    }

    public void sendHello2() {
        User u = new User();
        u.setId("1");
        u.setName("123");

        try {
            String json = objectMapper.writeValueAsString(u);
            Message message = MessageBuilder.withBody(json.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            this.template.convertAndSend("myQueue1", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendTopic() {
        User u = new User();
        u.setId("1");
        u.setName("123");
        template.convertAndSend("exchange", "topic.message", u);
    }

    public void sendTopic2() {
        User u = new User();
        u.setId("1");
        u.setName("123");
        template.convertAndSend("exchange", "topic.messages", u);
    }


    public void sendDelay(String msg) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("发送时间：" + sf.format(new Date()));

        template.convertAndSend("delayExchange", "delayQueue", msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 30000);
                return message;
            }
        });
    }

    public void sendMultiple() {
        for (int i = 0; i < 10; i++) {
            String msg = "你好 " + i;
            System.out.println("send =====:".concat(msg));
            template.convertAndSend("work-queue", msg);
        }
    }
}
