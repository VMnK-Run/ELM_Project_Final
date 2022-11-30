package com.tju.elmboot.util;

public class CreditConfigImpl implements CreditConfig{
    @Override
    public int calculateCreditByPrice(double price) {
        int credit = (int) (price / moneyForOneCredit);
        return credit;
    }
}
