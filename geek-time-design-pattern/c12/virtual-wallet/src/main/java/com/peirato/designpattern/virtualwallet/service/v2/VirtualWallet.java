package com.peirato.designpattern.virtualwallet.service.v2;

import com.peirato.designpattern.virtualwallet.InsufficientBalanceException;
import com.peirato.designpattern.virtualwallet.InvalidAmountException;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;

/**
 * @author Yang zeqi
 * @date 2019/12/2
 * @description: 充血模型
 */
public class VirtualWallet {

    private Long id;

    private Long createTime = System.currentTimeMillis();

    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId){
        this.id = preAllocatedId;
    }

    public BigDecimal balance(){
        return this.balance;
    }

    public void debit(BigDecimal amount){
        if(this.balance.compareTo(amount)<0){
            throw new InsufficientBalanceException();
        }
    }

    public void credit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <0){
            throw new InvalidAmountException();
        }

        this.balance.add(amount);
    }
}
