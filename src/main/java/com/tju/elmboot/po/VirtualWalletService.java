package com.tju.elmboot.po;

import com.tju.elmboot.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;

public class VirtualWalletService{
    private WalletService WalletService;

    public String userId;
    public String outId;
    public double amount;
    public int type;


    public void pay(){

    }
}
