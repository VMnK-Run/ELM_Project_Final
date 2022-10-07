package com.tju.elmboot.mapper;

import com.tju.elmboot.po.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BusinessMapper {

    //使用注解+xml结合的方式，简单的查询用注解，复杂查询、更新、插入操作写在xml映射文件中

    @Select("select * from business where orderTypeId=#{orderTypeId} order by businessId")
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

    @Select("select * from business where businessId=#{businessId}")
    public Business getBusinessById(Integer businessId);

    /**
     *  按默认顺序对商家进行排序
     * @param map
     *  输入当前经纬度组成的map
     * @return List<Business>
     *  返回按默认顺序排序的商家列表
     */
    @Select("select * from business order by businessId")
    public List<Business> listBusinessDefault(Map<String, String> map);

    /**
     *  按距离升序排序
     * @param map
     *  输入当前经纬度组成的map
     * @return List<Business>
     *  返回按距离升序排序的商家列表
     */
    public List<Business> listBusinessByDistance(Map<String, String> map);

    /**
     * 按销量对商家降序排序
     * @param map
     *  输入当前经纬度组成的map
     * @return List<Business>
     *  返回按销量降序排序的商家列表
     */
    @Select("select * from business t1  left join (select b.businessId,COUNT(o.businessId) as count from orders o right join business b on o.businessId=b.businessId group by b.businessId order by count desc) t0 on t1.businessId=t0.businessId order by t0.count desc")
    public List<Business>listBusinessBySales(Map<String, String> map);

    /**
     * 筛选符合条件的商家
     * @param orderTypeId
     *  商家分类
     * @param starPrice
     *  起送费上限
     * @param deliveryPrice
     *  配送费上限
     * @param distance
     *  到商家的距离上限
     * @param deliveryTime
     *  配送时间上限
     * @param longitude
     *  用户当前经度
     * @param latitude
     *  用户当前纬度
     * @return List<Business>
     *  返回符合条件的商家列表
     */
    public List<Business>listBusinessByConditions(Integer orderTypeId,Double starPrice,Double deliveryPrice,Double distance,Double deliveryTime,String longitude,String latitude);

    /**
     * 按关键字搜素商家，优先匹配商家名称，然后匹配食品名称并返回拥有该食品的商家 按匹配相似度排序返回
     * @param keywords
     *  关键字
     * @return List<Business>
     *  根据商家名称关键字或食品关键字返回的商家列表
     */
    @Select("select * from business b where b.businessName like concat('%',#{0},'%') union select * from business b where b.businessId = (select group_concat(businessId) from food f where f.foodName like concat('%',#{0},'%'))")
    public List<Business> listBusinessByKeyWords(String keywords);

    /**
     * 按评分降序排序
     * @param map
     *  输入当前经纬度组成的map
     * @return List<Business>
     *     返回按评分降序排序的商家列表
     */
    @Select("select * from business order by score desc")
    public List<Business> listBusinessByScore(Map<String, String> map);

    /**
     * 计算用户和商家的距离
     * @param map
     * 输入当前经纬度组成的map
     */
    public void refreshDistance(Map<String, String> map);

    /**
     * 计算配送时间
     * @param map
     * 输入当前经纬度组成的map
     */
    public void refreshTime(Map<String, String> map);

}