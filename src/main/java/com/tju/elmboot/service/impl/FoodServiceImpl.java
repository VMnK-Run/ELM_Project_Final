package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.BusinessMapper;
import com.tju.elmboot.mapper.FoodMapper;
import com.tju.elmboot.po.Food;
import com.tju.elmboot.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) {
        return foodMapper.listFoodByBusinessId(businessId);
    }
}
