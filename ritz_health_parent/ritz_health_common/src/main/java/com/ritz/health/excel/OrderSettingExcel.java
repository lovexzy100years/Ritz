package com.ritz.health.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;


public class OrderSettingExcel {

    @ExcelProperty("日期")
    private Date orderDate;//预约设置日期

    @ExcelProperty("可预约数量")
    private int number;//可预约人数


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
