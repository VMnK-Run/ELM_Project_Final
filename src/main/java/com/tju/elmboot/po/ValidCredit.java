package com.tju.elmboot.po;

import java.sql.Date;

public class ValidCredit {
    private Integer vcId;
    private String userId;
    private Integer credit;
    private Date createTime;
    private Date expiredTime;

    public Integer getVcId() {
        return vcId;
    }

    public void setVcId(Integer vcId) {
        this.vcId = vcId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
