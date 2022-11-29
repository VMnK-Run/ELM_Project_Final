package com.tju.elmboot.controller;

import com.tju.elmboot.po.CreditVO;
import com.tju.elmboot.po.ValidCredit;
import com.tju.elmboot.service.CartService;
import com.tju.elmboot.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CreditController")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @RequestMapping("/spendOrders")
    public int spendCredit(String userId, String channelId, int eventId, int credit) throws Exception {
        return creditService.spendCredit(userId, channelId, eventId, credit);
    }

    @RequestMapping("/getTotalCredit")
    public int getTotalCredit(String userId) throws Exception {
        return creditService.getTotalCredit(userId);
    }

    @RequestMapping("/getValidCredit")
    public List<ValidCredit> getValidCredits(String userId) throws Exception {
        return creditService.getValidCredits(userId);
    }

    @RequestMapping("/getCreditTotalDetails")
    public List<CreditVO> getCreditTotalDetails(String userId) throws Exception {
        return creditService.getCreditTotalDetails(userId);
    }

    @RequestMapping("/getCreditByParam")
    public List<CreditVO> getCreditTotalDetailsByParam(String userId, int param) throws Exception {
        return creditService.getCreditByParam(userId, param);
    }
}
