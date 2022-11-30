package com.tju.elmboot.po;

import com.tju.elmboot.util.SQLTimeUtil;

import java.util.ArrayList;
import java.util.List;

public class CreditBO{
    private String userId;
    private int totalCredit;
    private List<ValidCredit> validCredits;

    private List<ValidCredit> validCreditsOrderByTime;

    private List<Integer> tempIdOfRemove;

    private ValidCredit tempCreditOfAdd;

    public int getTotalCredit() {
        return this.totalCredit;
    }

    public List<Integer> getIndexOfRemove() {
        return this.tempIdOfRemove;
    }

    public ValidCredit getCreditOfAdd() {
        return this.tempCreditOfAdd;
    }
    public CreditBO(String userId, List<ValidCredit> validCredits) {
        this.userId = userId;
        this.validCredits = validCredits;
        int totalCredit = 0;
        for(ValidCredit validCredit : validCredits) {
            totalCredit += validCredit.getCredit();
        }
        this.totalCredit = totalCredit;
        this.tempIdOfRemove = new ArrayList<>();
    }

    public Boolean spendCredit(int credit) {
        this.validCreditsOrderByTime = this.validCredits;
        if(getTotalCredit() < credit) {
            return false;
        }
        int nowSum = 0;
        int index = 0;
        for(ValidCredit validCredit : validCreditsOrderByTime) {
            nowSum += validCredit.getCredit();
            if(nowSum >= credit) {
                break;
            }
            index++;
        }
        ValidCredit lastValidCredit = validCreditsOrderByTime.get(index);
        this.tempIdOfRemove.clear();
        for(int i = 0; i <= index; i++) {
            this.tempIdOfRemove.add(validCreditsOrderByTime.get(i).getVcId());
        }
        if(nowSum > credit) {
            ValidCredit validCredit = new ValidCredit();
            validCredit.setUserId(userId);
            validCredit.setCredit(nowSum - credit);
            validCredit.setCreateTime(lastValidCredit.getCreateTime());
            validCredit.setExpiredTime(lastValidCredit.getExpiredTime());
            this.tempCreditOfAdd = validCredit;
        } else {
            this.tempCreditOfAdd = null;
        }
        return true;
    }
}
