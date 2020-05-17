package com.peirato.demo.response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController("test")
public class DemoController {

    @GetMapping("demo")
    public TestDemoVO getTestDemo(){
        if(new Random().nextInt(2) % 2 ==0){
            return TestDemoVO.builder().name("test").status(1).build();
        }else{
            throw new RuntimeException("error");
        }
    }
}
