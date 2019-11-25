package com.peirato.springbucks.repository;

import com.peirato.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yang zeqi
 * @date 2019/11/8
 * @description:
 */

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
}
