package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.WalletMapper;
import com.tju.elmboot.po.VirtualWallet;
import com.tju.elmboot.po.VirtualWalletTransaction;
import com.tju.elmboot.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletMapper walletMapper;
    public VirtualWallet getWalletMessage(String userId){
        return walletMapper.getWalletMessage(userId);
    }

    @Override
    public int updataBalance(String userId, double balance) {
        return walletMapper.updataBalance(userId,balance);
    }

    public int pay(String userId,String outId,double amount,Integer type){
        double thisbalance=getWalletMessage(userId).getBalance();
        double outbalance=getWalletMessage(outId).getBalance();
        int now=updataBalance(outId,outbalance+amount)+updataBalance(userId,thisbalance-amount);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        int p=walletMapper.saveTransaction(0,formatter.format(date),amount,type,outId,userId);
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
}
