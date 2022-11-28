package com.tju.elmboot.service;

import com.tju.elmboot.po.CreditVO;
import com.tju.elmboot.po.ValidCredit;

import java.util.ArrayList;
import java.util.List;

public interface CreditService {

    public int getTotalCredit(String userId);

    public List<CreditVO> getCreditTotalDetails(String userId);

    public List<CreditVO> getCreditByParam(String userId, int param);

    public int saveCreditByOrder(int orderId);

    public int saveCreditByComment(int cmId);

    public int spendCredit(String userId, String channelId, int eventId, int credit);

    List<ValidCredit> getValidCredits(String userId);

    public int saveCreditForNewUser(String userId);

    public void updateCredit(String userId);
}
