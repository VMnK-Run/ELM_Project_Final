package com.tju.elmboot.service;

import com.tju.elmboot.po.VirtualWallet;
import com.tju.elmboot.po.VirtualWalletTransaction;

import java.util.List;

public interface WalletService {
    public VirtualWallet getWalletMessage(String userId);

    public int updataBalance(String userId,double balance);

    public int pay(String outId,double amount,Integer type);

    public List<VirtualWalletTransaction> listTransaction(String userId);

    public int saveWallet(String userId,double balance);
}
