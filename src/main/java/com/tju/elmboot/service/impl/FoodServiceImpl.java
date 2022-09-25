package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.BusinessMapper;
import com.tju.elmboot.mapper.CommentMapper;
import com.tju.elmboot.mapper.FoodMapper;
import com.tju.elmboot.po.Business;
import com.tju.elmboot.po.Comment;
import com.tju.elmboot.po.Food;
import com.tju.elmboot.service.FoodService;
import com.tju.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private BusinessMapper businessMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) {
        return foodMapper.listFoodByBusinessId(businessId);
    }

    @Override
    public Food getFoodByRandom() {
        List<Food> foodList = foodMapper.listAllFood();
        Map<Food, Double> foodMap = new HashMap<>();
        double maxStar = -0.1;
        for (Food food : foodList) {
            double averageStar = this.getAverageStarOfFood(food);
            if(averageStar >= maxStar) {
                maxStar = averageStar;
                foodMap.put(food, averageStar);
            }
        }

        List<Map.Entry<Food, Double>> list =new ArrayList<>(foodMap.entrySet());;
        list.sort(((o1, o2) -> o2.getValue().compareTo(o1.getValue())));

        List<Food> candidate = new ArrayList<>();
        int num = 0;
        for (Map.Entry<Food, Double> entry : list) {
            candidate.add(entry.getKey());
            num++;
            if(num >= 5) {
                break;
            }
        }
        Random random = new Random();
        Collections.shuffle(candidate, random);
        return candidate.get(0);
    }

    @Override
    public List<Food> listFoodByTime() {
        List<Food> res = new ArrayList<>();
        String currentTime = CommonUtil.getCurrentDate();
        int hour = Integer.parseInt(currentTime.substring(11, 13));
        List<Integer> candidateTypes = new ArrayList<>();
        if(5 <= hour && hour <= 10) { // 早上
            candidateTypes.add(2);
            candidateTypes.add(7);
            candidateTypes.add(9);
        } else if (hour <= 15) { // 中午
            candidateTypes.add(1);
            candidateTypes.add(4);
            candidateTypes.add(5);
            candidateTypes.add(8);
            candidateTypes.add(9);
            candidateTypes.add(10);
        } else { // 晚上
            candidateTypes.add(1);
            candidateTypes.add(5);
            candidateTypes.add(6);
            candidateTypes.add(7);
        }

        Map<Food, Double> map = new HashMap<>();

        for (int typeID : candidateTypes) {
            List<Business> businessList = businessMapper.listBusinessByOrderTypeId(typeID);
            for (Business business : businessList) {
                List<Food> foodList = foodMapper.listFoodByBusinessId(business.getBusinessId());
                for (Food food : foodList) {
                    double averageStar = this.getAverageStarOfFood(food);
                    map.put(food, averageStar);
                }
            }
        }
        List<Map.Entry<Food, Double>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Set<Integer> typeSet = new HashSet<>();
        int num = 0;
        int randomNum = CommonUtil.getRandomNumber(hour);
        for(Map.Entry<Food, Double> entry : list) {
            Food food = entry.getKey();
            Business business = businessMapper.getBusinessById(food.getBusinessId());
            if(!typeSet.contains(business.getOrderTypeId())) {
                typeSet.add(business.getOrderTypeId());
                res.add(food);
                num++;
            }
            if(num == randomNum) {
                break;
            }
        }

        return res;
    }


    private Double getAverageStarOfFood(Food food) {
        Comment comment = new Comment();
        comment.setFoodId(food.getFoodId());
        List<Comment> commentList = commentMapper.listComment(comment);
        double averageStar = 0;
        if (!commentList.isEmpty()) {
            int num = commentList.size();
            long totalStar = 0;
            for (Comment c : commentList) {
                totalStar += c.getStar();
            }
            averageStar = (double) (totalStar) / num;
        } else {
            averageStar = 5.0;
        }
        return averageStar;
    }
}
