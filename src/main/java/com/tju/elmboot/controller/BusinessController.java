package com.tju.elmboot.controller;

import com.tju.elmboot.po.Business;
import com.tju.elmboot.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/BusinessController")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping("/listBusinessByOrderTypeId")
    public List<Business> listBusinessByOrderTypeId(Business business) throws Exception {
        return businessService.listBusinessByOrderTypeId(business.getOrderTypeId());
    }

    @RequestMapping("/getBusinessById")
    public Business getBusinessById(Business business) throws Exception {
        return businessService.getBusinessById(business.getBusinessId());
    }

    @RequestMapping("/listBusinessDefault")
    public List<Business> listBusinessDefault() throws Exception {
        return businessService.listBusinessDefault();
    }

    @RequestMapping("/listBusinessByDistance")
    public List<Business> listBusinessByDistance(String longitude,String latitude) throws Exception {
        return businessService.listBusinessByDistance(longitude, latitude);
    }

    @RequestMapping("/listBusinessBySales")
    public List<Business> listBusinessBySales() throws Exception {
        return businessService.listBusinessBySales();
    }

    //暂未实现的功能:筛选

    @RequestMapping("/listBusinessByConditions")
    public List<Business> listBusinessByConditions(Integer orderTypeId,Double starPrice,Double deliveryPrice,Double distance,Double deliveryTime,Double longitude,Double latitude) throws Exception {
        return businessService.listBusinessByConditions(orderTypeId,starPrice,deliveryPrice,distance,deliveryTime,longitude,latitude);
    }

    @RequestMapping("/listBusinessByKeyWords")
    public List<Business> listBusinessByKeyWords(String KeyWord) throws Exception {
        return businessService.listBusinessByKeyWords(KeyWord);
    }
} 
