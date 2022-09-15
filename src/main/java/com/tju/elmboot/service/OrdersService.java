package com.tju.elmboot.service;

import com.tju.elmboot.po.OrderDetailet;
import com.tju.elmboot.po.Orders;

import java.util.List;

public interface OrdersService {

    public int createOrders(Orders orders);

    public Orders getOrdersById(Integer orderID);

    public List<Orders> listOrdersByUserId(String userId);
}
