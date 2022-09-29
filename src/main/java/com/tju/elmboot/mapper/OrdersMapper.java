package com.tju.elmboot.mapper;

import com.tju.elmboot.po.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrdersMapper {
    public void saveOrders(Orders orders);

    public int createOrders(Orders orders);

    public Orders getOrdersById(Integer orderID);

    public List<Orders> listOrdersByUserId(String userId);
}
