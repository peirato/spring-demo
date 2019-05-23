package com.peirato.dome;

import com.peirato.dome.direct.HelloSender;
import com.peirato.dome.fanout.FanoutSender;
import com.peirato.dome.topic.TopSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDemoApplicationTests {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void contextLoads() {
    }


    private static int i = 0;

    @Test
    public void send() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i < 100; i++) {
                    helloSender.send("num:" + i + "-thread:1");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i < 100; i++) {
                    helloSender.send("num:" + i + "-thread:2");
                }
            }
        }).start();

    }

    @Autowired
    private TopSender topSender;

    @Test
    public void topicSend() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                topSender.send1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                topSender.send2();
            }
        }).start();
    }

    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void fanoutSend(){

        fanoutSender.send();
    }



}
