package com.tju.elmboot.controller;

import com.tju.elmboot.po.Business;
import com.tju.elmboot.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//等于Controller+ResponseBody
@RequestMapping("/BusinessController")
public class BusinessController {

    //自动注入一个对象
    @Autowired
    private BusinessService businessService;


    @RequestMapping("/listBusinessByOrderTypeId")
    public List<Business> listBusinessByOrderTypeId(Business business) throws Exception {
        if(businessService.listBusinessByOrderTypeId(business.getOrderTypeId())!=null) {
            return businessService.listBusinessByOrderTypeId(business.getOrderTypeId());
        }
        else {
            System.out.println("123");
            return null;
        }

    }

    @RequestMapping("/getBusinessById")
    public Business getBusinessById(Business business) throws Exception {
        return businessService.getBusinessById(business.getBusinessId());
    }

    @RequestMapping("/listBusinessDefault")
    public List<Business> listBusinessDefault(String longitude,String latitude) throws Exception {
        return businessService.listBusinessDefault(longitude, latitude);
    }

    @RequestMapping("/listBusinessByDistance")
    public List<Business> listBusinessByDistance(String longitude,String latitude) throws Exception {
        return businessService.listBusinessByDistance(longitude, latitude);
    }

    @RequestMapping("/listBusinessBySales")
    public List<Business> listBusinessBySales(String longitude,String latitude) throws Exception {
        return businessService.listBusinessBySales(longitude, latitude);
    }

    @RequestMapping("/listBusinessByConditions")
    public List<Business> listBusinessByConditions(Integer orderTypeId,Double starPrice,Double deliveryPrice,Double distance,Double deliveryTime,String longitude,String latitude) throws Exception {
        return businessService.listBusinessByConditions(orderTypeId,starPrice,deliveryPrice,distance,deliveryTime,longitude,latitude);
    }

    @RequestMapping("/listBusinessByKeyWords")
    public List<Business> listBusinessByKeyWords(String KeyWord) throws Exception {
        return businessService.listBusinessByKeyWords(KeyWord);
    }

    @RequestMapping("/listBusinessByScore")
    public List<Business> listBusinessByScore(String longitude, String latitude) throws Exception {
        return businessService.listBusinessByScore(longitude, latitude);
    }
} 
