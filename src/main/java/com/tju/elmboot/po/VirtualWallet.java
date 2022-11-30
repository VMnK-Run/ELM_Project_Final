package com.tju.elmboot.po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VirtualWallet {
    public String userId;
    public String createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Double balance;

    public void withdraw(Double amount){
        if(this.balance - amount >= 0) {
            this.balance -= amount;
        }
        else this.balance = Double.valueOf(0);
    }

    public void fund(Double amount){
        this.balance += amount;
    }
}
