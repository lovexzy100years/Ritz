package com.ritz.health.service.therad;

import com.ritz.health.excel.OrderSettingExcel;
import com.ritz.health.mapper.IOrderSettingMapper;
import com.ritz.health.pojo.OrderSetting;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BatchInsertOrderSettingExcelRunnalbe implements Runnable {

    private List<OrderSettingExcel> orderSettingExcelList;

    private CountDownLatch end;//值等于 线程数  没完成一次插入 值应该减1

    private IOrderSettingMapper orderSettingMapper;


    public BatchInsertOrderSettingExcelRunnalbe() {
    }

    public BatchInsertOrderSettingExcelRunnalbe(List<OrderSettingExcel> orderSettingExcelList, CountDownLatch end, IOrderSettingMapper orderSettingMapper) {
        this.orderSettingExcelList = orderSettingExcelList;
        this.end = end;
        this.orderSettingMapper = orderSettingMapper;
    }

    @Override
    public void run() {
        //主要是王mysql中进行插入
        if(orderSettingExcelList != null && orderSettingExcelList.size() > 0){
            /*orderSettingMapper.batchInsertOrderSetting(orderSettingExcelList);*/
            for (OrderSettingExcel orderSettingExcel : orderSettingExcelList) {
                OrderSetting orderSetting = new OrderSetting();
                BeanUtils.copyProperties(orderSettingExcel,orderSetting);
                orderSettingMapper.insertOrderSetting(orderSetting);
            }

        }

        end.countDown();//减1
    }
}
