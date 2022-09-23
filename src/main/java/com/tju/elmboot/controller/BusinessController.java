package com.tju.elmboot.controller;

import com.tju.elmboot.po.Business;
import com.tju.elmboot.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
