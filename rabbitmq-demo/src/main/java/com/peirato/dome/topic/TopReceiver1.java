package com.peirato.dome.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message")
public class TopReceiver1 {

    @RabbitHandler
    public void process(String msg){
        System.out.println("TopReceiver1 :"+msg);
    }
}
