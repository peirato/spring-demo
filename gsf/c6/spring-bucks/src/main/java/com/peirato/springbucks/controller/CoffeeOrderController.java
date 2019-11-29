package com.peirato.springbucks.controller;

import com.peirato.springbucks.controller.request.NewOrderRequest;
import com.peirato.springbucks.model.Coffee;
import com.peirato.springbucks.model.CoffeeOrder;
import com.peirato.springbucks.service.CoffeeOrderService;
import com.peirato.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yang zeqi
 * @date 2019/11/25
 * @description:
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

    @GetMapping("/{id}")
    public CoffeeOrder getCoffeeOrder(@PathVariable Long id) {
        CoffeeOrder order = orderService.get(id);
        log.info("Get Order: {}",order);
        return order;
    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder){
        log.info("Receive new Order :{}",newOrder);
        Coffee[] coffeeList =coffeeService.getCoffeeByName(newOrder.getItem()).toArray(new Coffee[]{});
        return orderService.creatOrder(newOrder.getCustomer(),coffeeList);


    }
}
