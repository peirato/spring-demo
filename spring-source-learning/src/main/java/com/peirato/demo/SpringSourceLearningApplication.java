package com.peirato.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringSourceLearningApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringSourceLearningApplication.class, args);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(args);
    }
}
