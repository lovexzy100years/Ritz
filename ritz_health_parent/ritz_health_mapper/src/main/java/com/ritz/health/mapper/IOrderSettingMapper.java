package com.ritz.health.mapper;


import com.ritz.health.excel.OrderSettingExcel;
import com.ritz.health.pojo.OrderSetting;

import java.util.HashMap;
import java.util.List;

public interface IOrderSettingMapper {

    void insertOrderSetting(OrderSetting orderSetting);


    OrderSetting findOrderSettingByOrderDate(String orderDateStr);

    void updateOrderSettingByOrderDate(HashMap<String, Object> map);

    List<OrderSetting> findOrderSettingListByDate(HashMap<String, Object> dateMap);

    void batchInsertOrderSetting(List<OrderSettingExcel> orderSettingExcelList);//mapper forEach标签
}
