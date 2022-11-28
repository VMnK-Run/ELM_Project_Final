package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.CommentMapper;
import com.tju.elmboot.mapper.CreditMapper;
import com.tju.elmboot.mapper.OrdersMapper;
import com.tju.elmboot.mapper.ValidCreditMapper;
import com.tju.elmboot.po.*;
import com.tju.elmboot.service.CreditService;
import com.tju.elmboot.util.ChannelId;
import com.tju.elmboot.util.CreditConfig;
import com.tju.elmboot.util.SQLTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditMapper creditMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ValidCreditMapper validCreditMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public int getTotalCredit(String userId) {
        List<ValidCredit> validCredits = getValidCredits(userId);
        int totalCredit = 0;
        for(ValidCredit validCredit : validCredits) {
            totalCredit += validCredit.getCredit();
        }
        return totalCredit;
    }

    @Override
    public List<CreditVO> getCreditTotalDetails(String userId) {
        return creditMapper.getCreditTotalDetails(userId);
    }

    @Override
    public List<CreditVO> getCreditByParam(String userId, int param) {
        if(param == 0) {
            return creditMapper.getCreditOfAdd(userId);
        } else if(param == 1) {
            return creditMapper.getCreditOfSpend(userId);
        } else {
            return creditMapper.getCreditOfOutTime(userId);
        }
    }

    @Override
    @Transactional
    public int saveCreditByOrder(int orderId) {
        Orders orders = ordersMapper.getOrdersById(orderId);
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setUserId(orders.getUserId());
        creditEntity.setChannelId(ChannelId.addCreditByOrder);
        creditEntity.setEventId(orderId);
        double price = orders.getOrderTotal();
        int credit = CreditConfig.calculateCreditByPrice(price);
        creditEntity.setCredit(credit);
        Date createTime = SQLTimeUtil.getNowDate();
        Date expiredTime = SQLTimeUtil.addDays(createTime, CreditConfig.validDays);
        creditEntity.setCreateTime(createTime);
        creditEntity.setExpiredTime(expiredTime);

        ValidCredit validCredit = new ValidCredit();
        validCredit.setUserId(orders.getUserId());
        validCredit.setCredit(credit);
        validCredit.setCreateTime(createTime);
        validCredit.setExpiredTime(expiredTime);

        validCreditMapper.saveValidCredit(validCredit);

        return creditMapper.saveCreditDetail(creditEntity);
    }

    @Override
    public int saveCreditByComment(int cmId) {
        Comment comment = commentMapper.getCommentById(cmId);
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setUserId(comment.getUserId());
        creditEntity.setChannelId(ChannelId.addCreditByComment);
        creditEntity.setEventId(cmId);
        creditEntity.setCreditDetailId(CreditConfig.creditForOneComment);
        Date createTime = SQLTimeUtil.getNowDate();
        Date expiredTime = SQLTimeUtil.addDays(createTime, CreditConfig.validDays);
        creditEntity.setCreateTime(createTime);
        creditEntity.setExpiredTime(expiredTime);

        ValidCredit validCredit = new ValidCredit();
        validCredit.setUserId(comment.getUserId());
        validCredit.setCredit(CreditConfig.creditForOneComment);
        validCredit.setCreateTime(createTime);
        validCredit.setExpiredTime(expiredTime);

        System.out.println(validCredit.getCredit());

        validCreditMapper.saveValidCredit(validCredit);

        return creditMapper.saveCreditDetail(creditEntity);
    }

    @Override
    @Transactional
    public int spendCredit(String userId, String channelId, int eventId, int credit) {
        if(getTotalCredit(userId) < credit) {
            return -1;
        }
        List<ValidCredit> validCreditList = validCreditMapper.getAllValidCredit(userId);
        int nowSum = 0;
        int index = 0;
        for(ValidCredit validCredit : validCreditList) {
            nowSum += validCredit.getCredit();
            if(nowSum >= credit) {
                break;
            }
            index++;
        }
        ValidCredit lastValidCredit = validCreditList.get(index);
        for(int i = 0; i <= index; i++) {
            validCreditMapper.removeValidCreditById(validCreditList.get(i).getVcId());
        }
        if(nowSum > credit) {
            ValidCredit validCredit = new ValidCredit();
            validCredit.setUserId(userId);
            validCredit.setCredit(nowSum - credit);
            validCredit.setCreateTime(lastValidCredit.getCreateTime());
            validCredit.setExpiredTime(lastValidCredit.getExpiredTime());
            validCreditMapper.saveValidCredit(validCredit);
        }
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setUserId(userId);
        creditEntity.setChannelId(channelId);
        creditEntity.setEventId(eventId);
        creditEntity.setCredit(-credit);
        creditEntity.setCreateTime(SQLTimeUtil.getNowDate());
        creditEntity.setExpiredTime(null);
        return creditMapper.saveCreditDetail(creditEntity);
    }

    @Override
    public List<ValidCredit> getValidCredits(String userId) {
        return creditMapper.getValidCredit(userId);
    }

    @Override
    public int saveCreditForNewUser(String userId) {
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setUserId(userId);
        creditEntity.setChannelId(ChannelId.newUser);
        creditEntity.setEventId(-1);
        int credit = CreditConfig.newCredit;
        creditEntity.setCredit(credit);
        Date createTime = SQLTimeUtil.getNowDate();
        Date expiredTime = SQLTimeUtil.addDays(createTime, CreditConfig.validDays);
        creditEntity.setCreateTime(createTime);
        creditEntity.setExpiredTime(expiredTime);

        ValidCredit validCredit = new ValidCredit();
        validCredit.setUserId(userId);
        validCredit.setCredit(credit);
        validCredit.setCreateTime(createTime);
        validCredit.setExpiredTime(expiredTime);

        validCreditMapper.saveValidCredit(validCredit);

        return creditMapper.saveCreditDetail(creditEntity);
    }

    @Override
    @Transactional
    public void updateCredit(String userId) {
        List<ValidCredit> outTimeCredits = validCreditMapper.getOutTimeCredits(userId);
        for(ValidCredit validCredit : outTimeCredits) {
            validCreditMapper.removeValidCreditById(validCredit.getCredit());
            CreditEntity creditEntity = new CreditEntity();
            creditEntity.setUserId(userId);
            creditEntity.setChannelId(ChannelId.outTime);
            creditEntity.setEventId(-1);
            creditEntity.setCredit(-validCredit.getCredit());
            creditEntity.setCreateTime(validCredit.getExpiredTime());
            creditEntity.setExpiredTime(null);
            creditMapper.saveCreditDetail(creditEntity);
        }
    }
}
