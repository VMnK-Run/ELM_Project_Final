package com.tju.elmboot.po;

public class TransactionEntity extends VirtualWalletTransaction{
    public int transactionType;
    @Override
    public int getTransactionType() {
        return transactionType;
    }

    @Override
    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }
}
