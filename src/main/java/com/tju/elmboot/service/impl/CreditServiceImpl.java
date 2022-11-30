package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.CommentMapper;
import com.tju.elmboot.mapper.CreditMapper;
import com.tju.elmboot.mapper.OrdersMapper;
import com.tju.elmboot.mapper.ValidCreditMapper;
import com.tju.elmboot.po.*;
import com.tju.elmboot.service.CreditService;
import com.tju.elmboot.util.ChannelId;
import com.tju.elmboot.util.CreditConfig;
import com.tju.elmboot.util.CreditConfigImpl;
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
        CreditBO creditBO = new CreditBO(userId, getValidCredits(userId));
        return creditBO.getTotalCredit();
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
        CreditConfig creditConfig = new CreditConfigImpl();
        int credit = creditConfig.calculateCreditByPrice(price);
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
        /*TODO:改成充血模型，也就是BO弄成充血模型,方法可以是将Entity转换成这个BO，即一个人的可用积分是一个充血模型类，从这个可用积分里进行扣除
        * */
        CreditBO creditBO = new CreditBO(userId, validCreditMapper.getAllValidCredit(userId));
        boolean success = creditBO.spendCredit(credit);
        if(!success) {
            return -1;
        }
        for (int i : creditBO.getIndexOfRemove()) {
            validCreditMapper.removeValidCreditById(i);
        }
        if(creditBO.getCreditOfAdd() != null) {
            validCreditMapper.saveValidCredit(creditBO.getCreditOfAdd());
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
