package com.tju.elmboot.service;

import com.tju.elmboot.po.DeliveryAddress;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeliveryAddressService {

    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);

    public DeliveryAddress getDeliveryAddressById(Integer daId);
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress);
    public int removeDeliveryAddress(Integer daId);

}
