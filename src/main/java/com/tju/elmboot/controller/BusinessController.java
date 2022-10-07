package com.tju.elmboot.controller;

import com.tju.elmboot.po.Business;
import com.tju.elmboot.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
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

    /**
     * 按默认顺序对商家进行排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @RequestMapping("/listBusinessDefault")
    public List<Business> listBusinessDefault(String longitude,String latitude) throws Exception {
        return businessService.listBusinessDefault(longitude, latitude);
    }

    /**
     * 按距离升序排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @RequestMapping("/listBusinessByDistance")
    public List<Business> listBusinessByDistance(String longitude,String latitude) throws Exception {
        return businessService.listBusinessByDistance(longitude, latitude);
    }

    /**
     * 按销量对商家降序排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @RequestMapping("/listBusinessBySales")
    public List<Business> listBusinessBySales(String longitude,String latitude) throws Exception {
        return businessService.listBusinessBySales(longitude, latitude);
    }

    /**
     * 筛选符合条件的商家
     * @param orderTypeId
     * 商家分类
     * @param starPrice
     * 起送费上限
     * @param deliveryPrice
     * 配送费上限
     * @param distance
     * 距离上限
     * @param deliveryTime
     * 配送时间上限
     * @param longitude
     * 用户当前经度
     * @param latitude
     * 用户当前纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @RequestMapping("/listBusinessByConditions")
    public List<Business> listBusinessByConditions(Integer orderTypeId,Double starPrice,Double deliveryPrice,Double distance,Double deliveryTime,String longitude,String latitude) throws Exception {
        return businessService.listBusinessByConditions(orderTypeId,starPrice,deliveryPrice,distance,deliveryTime,longitude,latitude);
    }

    /**
     * 按关键字搜素商家，优先匹配商家名称，然后匹配食品名称并返回拥有该食品的商家 按匹配相似度排序返回
     * @param keywords
     * 关键字
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @RequestMapping("/listBusinessByKeyWords")
    public List<Business> listBusinessByKeyWords(String keywords) throws Exception {
        keywords = keywords.replace(" ", "");
        return businessService.listBusinessByKeyWords(keywords);
    }

    /**
     * 按评分降序排序
     * @param longitude
     * 用户当前经度
     * @param latitude
     * 用户所在纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @RequestMapping("/listBusinessByScore")
    public List<Business> listBusinessByScore(String longitude, String latitude) throws Exception {
        return businessService.listBusinessByScore(longitude, latitude);
    }
} 
