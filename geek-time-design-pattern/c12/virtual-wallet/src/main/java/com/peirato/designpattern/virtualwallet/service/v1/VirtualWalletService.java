package com.peirato.designpattern.virtualwallet.service.v1;

import com.peirato.designpattern.virtualwallet.NoSufficientBalanceException;
import com.peirato.designpattern.virtualwallet.repository.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Yang zeqi
 * @date 2019/12/2
 * @description: 贫血模型
 */
public class VirtualWalletService {

    @Resource
    private VirtualWalletRepository walletRepository;

    @Resource
    private VirtualWalletTransactionRepository transactionRepository;

    public VirtualWalletBo getVirtualWallet(Long walletId) {

        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        VirtualWalletBo walletBo = covert(walletEntity);
        return walletBo;

    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepository.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity =
                walletRepository.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException();
        }
        walletRepository.updateBalance(walletId, balance.subtract(amount));

    }

    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity =
                walletRepository.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        walletRepository.updateBalance(walletId, balance.add(amount));
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionEntity.setStatus(Status.TO_BE_EXECUTED);
        Long transactionId = transactionRepository.saveTransaction(transactionEntity);
        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (NoSufficientBalanceException e) {
            transactionRepository.updateStatus(transactionId, Status.CLOSED);
        } catch (Exception e) {
            transactionRepository.updateStatus(transactionId, Status.FAILED);
        }
        transactionRepository.updateStatus(transactionId, Status.EXECUTED);
    }

    private VirtualWalletBo covert(VirtualWalletEntity walletEntity) {
        return null;
    }
}
