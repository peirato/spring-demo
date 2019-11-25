package com.peirato.springbucks.service;

import com.peirato.springbucks.model.Coffee;
import com.peirato.springbucks.model.CoffeeOrder;
import com.peirato.springbucks.model.OrderState;
import com.peirato.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author Yang zeqi
 * @date 2019/11/8
 * @description:
 */
@Slf4j
@Service
@Transactional
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffee) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffee))
                .state(OrderState.INIT)
                .build();

        CoffeeOrder saved = orderRepository.save(order);
        log.info("New Order :{}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }

        order.setState(state);
        orderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;

    }

}
