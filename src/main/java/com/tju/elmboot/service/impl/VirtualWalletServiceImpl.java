package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.WalletMapper;
import com.tju.elmboot.mapper.WalletTransactionMapper;
import com.tju.elmboot.po.*;
import com.tju.elmboot.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VirtualWalletServiceImpl implements WalletService {
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private WalletTransactionMapper transactionMapper;
    public VirtualWallet getWalletMessage(String userId){
        return walletMapper.getWalletMessage(userId);
    }

    @Override
    public int updataBalance(String userId, double balance) {
        return walletMapper.updataBalance(userId,balance);
    }

    public int pay(String outId,double amount,Integer type){
        double elmbalance=getWalletMessage("elm10086").getBalance();
        double outbalance=getWalletMessage(outId).getBalance();
        int now=updataBalance(outId,outbalance-amount)+updataBalance("elm10086",elmbalance+amount);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        int p=walletMapper.saveTransaction(0,formatter.format(date),amount,type,"elm10086",outId);
        return now;
    }

    public List<VirtualWalletTransaction> listTransaction(String userId){
        return walletMapper.listTransaction(userId);
    }

    public int saveWallet(String userId,double balance){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return walletMapper.saveWallet(userId,formatter.format(date),balance);
    }

    @Transactional
    @Override
    public void withdraw(String outId, Double amount) {
        VirtualWalletBO walletBO = new VirtualWalletBO(walletMapper.getWalletMessage(outId));
        walletBO.withdraw(amount);
        walletMapper.updateWallet(walletBO);

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setAmount(amount);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        transactionEntity.setTransactionTime(formatter.format(date));

        transactionEntity.setTransactionType(1);

        transactionEntity.setOutId(outId);
        transactionEntity.setInId("");

        transactionMapper.saveTransaction(transactionEntity);
    }

    @Transactional
    @Override
    public void fund(String inId, Double amount) {
        VirtualWalletBO walletBO = new VirtualWalletBO(walletMapper.getWalletMessage(inId));
        walletBO.fund(amount);
        walletMapper.updateWallet(walletBO);

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setAmount(amount);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        transactionEntity.setTransactionTime(formatter.format(date));

        transactionEntity.setTransactionType(0);

        transactionEntity.setInId(inId);
        transactionEntity.setOutId("");

        transactionEntity.setAmount(amount);

        transactionMapper.saveTransaction(transactionEntity);
    }
}
