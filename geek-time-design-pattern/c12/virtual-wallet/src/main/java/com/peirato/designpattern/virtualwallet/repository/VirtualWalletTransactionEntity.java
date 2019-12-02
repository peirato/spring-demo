package com.peirato.designpattern.virtualwallet.repository;

import java.math.BigDecimal;

/**
 * @author Yang zeqi
 * @date 2019/12/2
 * @description:
 */
public class VirtualWalletTransactionEntity {

    private BigDecimal amount;

    private Long CreateTime;

    private Long fromWalletId;

    private Integer status;

    private Long ToWalletId;

    public Long getToWalletId() {
        return ToWalletId;
    }

    public void setToWalletId(Long toWalletId) {
        ToWalletId = toWalletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public Long getFromWalletId() {
        return fromWalletId;
    }

    public void setFromWalletId(Long fromWalletId) {
        this.fromWalletId = fromWalletId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
