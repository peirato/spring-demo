package com.peirato.springbucks.service;

import com.peirato.springbucks.model.Coffee;
import com.peirato.springbucks.model.CoffeeOrder;
import com.peirato.springbucks.model.OrderState;
import com.peirato.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Yang zeqi
 * @date 2019/11/25
 * @description:
 */
@Service
@Slf4j
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder get(Long id) {
        return orderRepository.getOne(id);
    }

    public CoffeeOrder creatOrder(String customer, Coffee... coffee) {
        CoffeeOrder order = CoffeeOrder.builder().customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffee)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = orderRepository.save(order);
        return saved;
    }
}
