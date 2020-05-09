package com.peirato.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@SpringBootApplication
@Transactional
public class TransactionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionDemoApplication.class, args);

        // 手动回滚当前事务
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

}
