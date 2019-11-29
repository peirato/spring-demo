package com.peirato.springbucks.repository;

import com.peirato.springbucks.model.Coffee;
import org.springframework.boot.autoconfigure.transaction.jta.JtaProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yang zeqi
 * @date 2019/11/25
 * @description:
 */
public interface CoffeeRepository extends JpaRepository<Coffee,Long> {

    List<Coffee> findByNameInOrOrderById(List<String> list);




}
