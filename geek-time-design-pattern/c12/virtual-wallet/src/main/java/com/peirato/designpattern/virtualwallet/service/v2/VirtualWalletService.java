package com.peirato.designpattern.virtualwallet.service.v2;

import com.peirato.designpattern.virtualwallet.repository.VirtualWalletEntity;
import com.peirato.designpattern.virtualwallet.repository.VirtualWalletRepository;
import com.peirato.designpattern.virtualwallet.repository.VirtualWalletTransactionRepository;

import java.math.BigDecimal;

/**
 * @author Yang zeqi
 * @date 2019/12/2
 * @description: 充血模型
 */
public class VirtualWalletService {

    private VirtualWalletRepository walletRepository;

    private VirtualWalletTransactionRepository transactionRepository;

    public VirtualWallet getVirtualWallet(Long walletId){
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        return wallet;
    }

    public BigDecimal getBalance(Long walledId){
        return walletRepository.getBalance(walledId);
    }

    public void debit(Long walletId,BigDecimal amount){
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        walletRepository.updateBalance(walletId,wallet.balance());
    }

    public void credit(Long walletId,BigDecimal amount){
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        walletRepository.updateBalance(walletId,wallet.balance());
    }

    public void transfer(Long fromWalletId,Long toWalletId,BigDecimal amount){

    }

    private VirtualWallet convert(VirtualWalletEntity walletEntity) {
        return null;
    }
}
