package com.peirato.dome.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class TopSender {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender1 : " + context);
        rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender2 : " + context);
        rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }

}
