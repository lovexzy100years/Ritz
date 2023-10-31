package com.ritz.health.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ritz.health.excel.OrderSettingExcel;
import com.ritz.health.service.IOrderSettingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderSettingExcelListener extends AnalysisEventListener<OrderSettingExcel> {

    private List<OrderSettingExcel> orderSettingExcelList = new ArrayList<>();

    @Resource
    private IOrderSettingService orderSettingService;

    @Override
    public void invoke(OrderSettingExcel data, AnalysisContext context) {
        orderSettingExcelList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //TODO 批量数据的优化
        //调用service 插入数据到数据库
        /*for (OrderSettingExcel orderSettingExcel : orderSettingExcelList) {
            orderSettingService.insertOrderSetting(orderSettingExcel);
        }*/
        orderSettingService.batchInsertOrderSetting(orderSettingExcelList);
        orderSettingExcelList.clear();//清空集合
    }
}
