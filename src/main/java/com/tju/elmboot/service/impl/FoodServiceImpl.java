package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.BusinessMapper;
import com.tju.elmboot.mapper.FoodMapper;
import com.tju.elmboot.po.Business;
import com.tju.elmboot.po.Food;
import com.tju.elmboot.service.FoodService;
import com.tju.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) {
        return foodMapper.listFoodByBusinessId(businessId);
    }

    @Override
    public Food getFoodByRandom() {

        return null;
    }

    @Override
    public List<Food> listFoodByTime() {
        List<Food> res = new ArrayList<>();
        String currentTime = CommonUtil.getCurrentDate();
        int hour = Integer.parseInt(currentTime.substring(11, 13));
        List<Integer> candidateTypes = new ArrayList<>();
        if(5 <= hour && hour <= 10) { // 早上

        } else if (hour <= 15) { // 中午

        } else { // 晚上

        }
//        for (int typeID : candidateTypes) {
//            List<Business> businessList =
//        }
        return res;
    }
}
