package com.tju.elmboot.service;

import com.tju.elmboot.po.Food;

import java.util.List;

public interface FoodService {

    public List<Food> listFoodByBusinessId(Integer businessId);
}
