package com.peirato.designpattern.virtualwallet.repository;

import java.math.BigDecimal;

/**
 * @author Yang zeqi
 * @date 2019/12/2
 * @description:
 */

public class VirtualWalletEntity {

    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
