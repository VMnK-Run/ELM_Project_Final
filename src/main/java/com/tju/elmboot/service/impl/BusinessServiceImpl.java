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

    /**
     * 筛选指定类别的商家
     * @param orderTypeId
     * 商家类别
     * @return businessMapper
     * 调用mapper层
     */
    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
        return businessMapper.listBusinessByOrderTypeId(orderTypeId);
    }

    /**
     * 按商家序号选择商家
     * @param businessId
     * 商家序号
     * @return businessMapper
     * 调用mapper层
     */
    @Override
    public Business getBusinessById(Integer businessId) {
        return businessMapper.getBusinessById(businessId);
    }

    /**
     * 按默认顺序对商家进行排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessMapper
     * 调用mapper层
     */
    @Override
    public List<Business> listBusinessDefault(String longitude, String latitude) {
        //将用户经纬度封装在map中
        Map<String,String> map;
        map = lonLatCalculation(Double.valueOf(longitude),Double.valueOf(latitude),500000);
        map.put("longitude",longitude);
        map.put("latitude",latitude);
        businessMapper.refreshDistance(map);
        businessMapper.refreshTime(map);
        return businessMapper.listBusinessDefault(map);
    }

    /**
     * 按距离升序排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessMapper
     * 调用mapper层
     */
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

    /**
     * 按销量对商家降序排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessMapper
     * 调用mapper层
     */
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
     * @return businessMapper
     * 调用mapper层
     * @throws Exception
     */
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

    /**
     * 按关键字搜素商家，优先匹配商家名称，然后匹配食品名称并返回拥有该食品的商家 按匹配相似度排序返回
     * @param keywords
     * 关键字
     * @return businessMapper
     * 调用mapper层
     */
    @Override
    public List<Business> listBusinessByKeyWords(String keywords) {
        return businessMapper.listBusinessByKeyWords(keywords);
    }

    /**
     * 按评分降序排序
     * @param longitude
     * 用户当前经度
     * @param latitude
     * 用户所在纬度
     * @return businessMapper
     * 调用mapper层
     */
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
