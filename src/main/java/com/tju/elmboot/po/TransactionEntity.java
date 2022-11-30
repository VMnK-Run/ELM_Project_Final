package com.tju.elmboot.po;

public class TransactionEntity extends VirtualWalletTransaction{
    public int transactionId;
    public String transactionTime;
    public Double amount;
    public int transactionType;
    public String inId;
    public String outId;

    @Override
    public int getTransactionId() {
        return transactionId;
    }

    @Override
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String getTransactionTime() {
        return transactionTime;
    }

    @Override
    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public int getTransactionType() {
        return transactionType;
    }

    @Override
    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String getInId() {
        return inId;
    }

    @Override
    public void setInId(String inId) {
        this.inId = inId;
    }

    @Override
    public String getOutId() {
        return outId;
    }

    @Override
    public void setOutId(String outId) {
        this.outId = outId;
    }
}
