package com.tju.elmboot.po;

public class TransactionEntity extends VirtualWalletTransaction{
    public int transactionType;
    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }
}
