package com.tju.elmboot.po;

import java.util.Date;

public class VirtualWalletBO extends VirtualWallet{
    public void withdraw(Double amount){
        if(this.balance - amount >= 0) {
            this.balance -= amount;
        }
        else this.balance = Double.valueOf(0);
    }

    public void fund(Double amount){
        this.balance += amount;
    }

    public VirtualWalletBO(VirtualWallet wallet){
        this.balance = wallet.balance;
        this.userId = wallet.userId;
        this.createTime = wallet.createTime;
    }

}
