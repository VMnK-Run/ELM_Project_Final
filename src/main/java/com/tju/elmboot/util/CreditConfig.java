package com.tju.elmboot.util;

public interface CreditConfig {
    public static Integer moneyForOneCredit = 5; // 兑换一个积分需要的钱数

    public static Integer PointsForOneYuan = 50; // 50个积分抵扣一元
    public static Integer creditForOneComment = 1; // 一条评论的积分
    public static Integer validDays = 30; // 积分有效天数
    public static Integer newCredit = 300; // 新用户注册默认积分

    int calculateCreditByPrice(double price);

}
