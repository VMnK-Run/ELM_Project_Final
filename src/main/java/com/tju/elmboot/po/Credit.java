package com.tju.elmboot.po;

import java.sql.Date;

public class Credit {
    protected Integer creditDetailId;
    protected String userId;
    protected String channelId;
    protected Integer credit;
    protected Date createTime;
    protected Date expiredTime;

    public Integer getCreditDetailId() {
        return creditDetailId;
    }

    public void setCreditDetailId(Integer creditDetailId) {
        this.creditDetailId = creditDetailId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
