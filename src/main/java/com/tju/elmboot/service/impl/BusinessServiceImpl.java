package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.BusinessMapper;
import com.tju.elmboot.po.Business;
import com.tju.elmboot.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.tju.elmboot.util.DistanceUtils.lonLatCalculation;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;
    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
        return businessMapper.listBusinessByOrderTypeId(orderTypeId);
    }

    @Override
    public Business getBusinessById(Integer businessId) {
        return businessMapper.getBusinessById(businessId);
    }

    @Override
    public List<Business> listBusinessDefault(String longitude, String latitude) {
        Map<String,String> map;
        map = lonLatCalculation(Double.valueOf(longitude),Double.valueOf(latitude),500000);
        map.put("longitude",longitude);
        map.put("latitude",latitude);
        businessMapper.refreshDistance(map);
        businessMapper.refreshTime(map);
        return businessMapper.listBusinessDefault(map);
    }

    @Override
    public List<Business> listBusinessByDistance(String longitude, String latitude) {
        Map<String,String> map;
        map = lonLatCalculation(Double.valueOf(longitude),Double.valueOf(latitude),500000);
        map.put("longitude",longitude);
        map.put("latitude",latitude);
        businessMapper.refreshDistance(map);
        businessMapper.refreshTime(map);
        return businessMapper.listBusinessByDistance(map);
    }

    @Override
    public List<Business> listBusinessBySales(String longitude, String latitude) {
        Map<String,String> map;
        map = lonLatCalculation(Double.valueOf(longitude),Double.valueOf(latitude),500000);
        map.put("longitude",longitude);
        map.put("latitude",latitude);
        businessMapper.refreshDistance(map);
        businessMapper.refreshTime(map);
        return businessMapper.listBusinessBySales(map);
    }

    @Override
    public List<Business> listBusinessByConditions(Integer orderTypeId, Double starPrice, Double deliveryPrice, Double distance, Double deliveryTime, String longitude, String latitude) {
        if(longitude!="" && latitude!=""){
            Map<String,String> map;
            map = lonLatCalculation(Double.valueOf(longitude),Double.valueOf(latitude),500000);
            map.put("longitude",longitude);
            map.put("latitude",latitude);
            businessMapper.refreshDistance(map);
            businessMapper.refreshTime(map);
        }
        return businessMapper.listBusinessByConditions(orderTypeId,starPrice,deliveryPrice,distance,deliveryTime,longitude,latitude);
    }

    @Override
    public List<Business> listBusinessByKeyWords(String keywords) {
        return businessMapper.listBusinessByKeyWords(keywords);
    }

    @Override
    public List<Business> listBusinessByScore(String longitude, String latitude) {
        Map<String,String> map;
        map = lonLatCalculation(Double.valueOf(longitude),Double.valueOf(latitude),500000);
        map.put("longitude",longitude);
        map.put("latitude",latitude);
        businessMapper.refreshDistance(map);
        businessMapper.refreshTime(map);
        return businessMapper.listBusinessByScore(map);
    }
}
