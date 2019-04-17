package com.tim.consumer.reciver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkReceiver {
    private String receiverInstance;

    public WorkReceiver(String receiverInstance) {
        this.receiverInstance = receiverInstance;
    }

    @RabbitListener(queues = "work-queue")
    public void receive(Message str) {
        System.out.println(receiverInstance.concat(" =====: ").concat(str.toString()));
    }
}
