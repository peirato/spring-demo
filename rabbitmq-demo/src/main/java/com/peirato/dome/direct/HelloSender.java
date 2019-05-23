package com.peirato.dome.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class HelloSender {

    @Resource
    private AmqpTemplate amqpTemplate;

    public void send(String message){
        String context = "hello "+ new Date() +" - "+message;
        System.out.println("Sender :"+context);
        amqpTemplate.convertAndSend("hello",context);
    }
}
