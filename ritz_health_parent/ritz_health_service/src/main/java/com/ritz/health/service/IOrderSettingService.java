package com.ritz.health.service;

import com.ritz.health.dto.OrderSettingDTO;
import com.ritz.health.excel.OrderSettingExcel;
import com.ritz.health.pojo.OrderSetting;

import java.util.Date;
import java.util.List;

public interface IOrderSettingService {
    void insertOrderSetting(OrderSettingExcel orderSettingExcel);

    void updateOrderSettingByOrderDate(OrderSetting orderSetting);

    List<OrderSettingDTO> findOrderSettingListByDate(Date date);

    void batchInsertOrderSetting(List<OrderSettingExcel> orderSettingExcelList);
}
