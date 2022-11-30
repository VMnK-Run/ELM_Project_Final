package com.tju.elmboot.controller;

import com.tju.elmboot.po.VirtualWallet;
import com.tju.elmboot.po.VirtualWalletTransaction;
import com.tju.elmboot.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/WalletController")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @RequestMapping("/wallet")
    public VirtualWallet getWalletMessage(String userId){
        return walletService.getWalletMessage(userId);
    }

    @RequestMapping("/balance")
    public double getWalletBanlance(String userId){
        return walletService.getWalletMessage(userId).getBalance();
    }

    @RequestMapping("/pay")
    public int pay(String outId,double amount,Integer type){
        return walletService.pay(outId, amount, type);
    }

    @RequestMapping("/transaction")
    public List<VirtualWalletTransaction> listTransaction(String userId){
        return walletService.listTransaction(userId);
    }

    @RequestMapping("/saveWallet")
    public int saveWallet(String userId,double balance){
        return walletService.saveWallet(userId,balance);
    }

    @RequestMapping("/fund")
    public void fund(String inId, Double amount){
        walletService.fund(inId, amount);
    }

    @RequestMapping("/withdraw")
    public void withdraw(String outId, Double amount){
        walletService.withdraw(outId, amount);
    }
}
