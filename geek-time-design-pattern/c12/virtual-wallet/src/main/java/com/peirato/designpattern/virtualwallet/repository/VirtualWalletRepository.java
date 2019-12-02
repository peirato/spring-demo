package com.peirato.designpattern.virtualwallet.repository;

import java.math.BigDecimal;

/**
 * @author Yang zeqi
 * @date 2019/12/2
 * @description:
 */

public class VirtualWalletRepository {
    public VirtualWalletEntity getWalletEntity(Long walletId) {
        return new VirtualWalletEntity();
    }

    public BigDecimal getBalance(Long walletId) {
        return new BigDecimal(0.0);
    }

    public void updateBalance(Long walletId, BigDecimal subtract) {

    }
}
