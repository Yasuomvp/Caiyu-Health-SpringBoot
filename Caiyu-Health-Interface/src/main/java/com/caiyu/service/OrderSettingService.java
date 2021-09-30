package com.caiyu.service;

import com.caiyu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    public void add(List<OrderSetting> orderSettingList) throws Exception;

    public List<Map> getOrderSettingByMonth(String date) throws Exception;

    public void editNumberByDate(OrderSetting orderSetting) throws Exception;


}
