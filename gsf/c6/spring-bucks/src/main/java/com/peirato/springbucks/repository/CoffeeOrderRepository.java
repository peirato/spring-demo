package com.peirato.springbucks.repository;

import com.peirato.springbucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yang zeqi
 * @date 2019/11/25
 * @description:
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder,Long> {
}
