package com.tju.elmboot.controller;

import com.tju.elmboot.po.Orders;
import com.tju.elmboot.service.CreditService;
import com.tju.elmboot.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/OrdersController")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CreditService creditService;

    @RequestMapping("/createOrders")
    public int createOrders(Orders orders) throws Exception {
        int odId = ordersService.createOrders(orders);
        int creditId = creditService.saveCreditByOrder(odId);
        return odId;
    }

    @RequestMapping("/getOrdersById")
    public Orders getOrdersById(Orders orders) throws Exception {
        return ordersService.getOrdersById(orders.getOrderId());
    }

    @RequestMapping("/listOrdersByUserId")
    public List<Orders> listOrdersByUserId(Orders orders) throws Exception {
        return ordersService.listOrdersByUserId(orders.getUserId());
    }
}
