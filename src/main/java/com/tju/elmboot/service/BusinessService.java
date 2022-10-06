package com.tju.elmboot.service;

import com.tju.elmboot.po.Business;

import java.util.List;

public interface BusinessService {

    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

    public Business getBusinessById(Integer businessId);

    public List<Business> listBusinessDefault(String longitude, String latitude);

    public List<Business> listBusinessByDistance(String longitude, String latitude);

    public List<Business> listBusinessBySales(String longitude, String latitude);

    public List<Business> listBusinessByKeyWords(String keywords);

    public List<Business> listBusinessByConditions(Integer orderTypeId, Double starPrice, Double deliveryPrice, Double distance, Double deliveryTime, String longitude, String latitude);

    public List<Business> listBusinessByScore(String longitude, String latitude);
}
