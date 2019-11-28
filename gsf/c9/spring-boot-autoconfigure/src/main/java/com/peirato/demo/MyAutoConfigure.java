package com.peirato.demo;

import geektime.spring.hello.greeting.GreetingApplicationRunner;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@Configurable
@ConditionalOnClass(GreetingApplicationRunner.class)
public class MyAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(GreetingApplicationRunner.class)
    @ConditionalOnProperty(name = "greeting.enable",havingValue = "true",matchIfMissing = true)
    public GreetingApplicationRunner getApplicationRunner(){
        return new GreetingApplicationRunner();
    }

}
