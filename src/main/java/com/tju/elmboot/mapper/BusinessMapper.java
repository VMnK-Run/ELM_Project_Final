package com.tju.elmboot.mapper;

import com.tju.elmboot.po.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BusinessMapper {

    @Select("select * from business where orderTypeId=#{orderTypeId} order by businessId")
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

    @Select("select * from business where businessId=#{businessId}")
    public Business getBusinessById(Integer businessId);

    @Select("select * from business order by businessId")
    public List<Business> listBusinessDefault(Map<String, String> map);

    public List<Business> listBusinessByDistance(Map<String, String> map);

    @Select("select * from business t1  left join (select b.businessId,COUNT(o.businessId) as count from orders o right join business b on o.businessId=b.businessId group by b.businessId order by count desc) t0 on t1.businessId=t0.businessId order by t0.count desc")
    public List<Business>listBusinessBySales(Map<String, String> map);

    public List<Business>listBusinessByConditions(Integer orderTypeId,Double starPrice,Double deliveryPrice,Double distance,Double deliveryTime,String longitude,String latitude);

    @Select("select * from business b where b.businessName like concat('%',#{0},'%') union select * from business b where b.businessId = (select group_concat(businessId) from food f where f.foodName like concat('%',#{0},'%'))")
    public List<Business> listBusinessByKeyWords(String keywords);

    @Select("select * from business order by score desc")
    public List<Business> listBusinessByScore(Map<String, String> map);

    public void refreshDistance(Map<String, String> map);

    public void refreshTime(Map<String, String> map);

}