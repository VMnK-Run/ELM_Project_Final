package com.tju.elmboot.service;

import com.tju.elmboot.po.Business;

import java.util.List;

public interface BusinessService {

    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

    public Business getBusinessById(Integer businessId);

    public List<Business> listBusinessDefault();

    public List<Business> listBusinessByDistance(String longitude, String latitude);

    public List<Business> listBusinessBySales();

    public List<Business> listBusinessByKeyWords(String KeyWord);

    public List<Business> listBusinessByConditions(Integer orderTypeId, Double starPrice, Double deliveryPrice, Double distance, Double deliveryTime, Double longitude, Double latitude);
}
