package com.tju.elmboot.service;

import com.tju.elmboot.po.Business;

import java.util.List;

public interface BusinessService {

    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

    public Business getBusinessById(Integer businessId);
}
