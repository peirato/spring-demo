package com.peirato.demo;

import com.peirato.demo.right.AbstractCart;
import groovy.util.logging.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("templatemethod")
public class TemplateAndFactoryController {

    private static Map<Long, Integer> items = new HashMap<>();

    static {
        items.put(1L, 2);
        items.put(2L, 4);
    }

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("right")
    public Cart right(@RequestParam int userId){
        String userCategory = Db.getUserCategory(userId);
        // 通过spring来实现工厂模式
        AbstractCart cart = (AbstractCart) applicationContext.getBean(userCategory+"UserCart");
        return cart.process(userId,items);
    }
}
