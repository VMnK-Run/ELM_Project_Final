package com.tju.elmboot.controller;

import com.tju.elmboot.po.Food;
import com.tju.elmboot.service.BusinessService;
import com.tju.elmboot.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/FoodController")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @RequestMapping("/listFoodByBusinessId")
    public List<Food> listFoodByBusinessId(Food food) throws Exception {
        return foodService.listFoodByBusinessId(food.getBusinessId());
    }
}
