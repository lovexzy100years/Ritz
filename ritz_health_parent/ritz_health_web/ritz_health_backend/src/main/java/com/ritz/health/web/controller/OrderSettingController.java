package com.ritz.health.web.controller;

import com.ritz.health.dto.OrderSettingDTO;
import com.ritz.health.entity.Result;
import com.ritz.health.message.MessageConstant;
import com.ritz.health.pojo.OrderSetting;
import com.ritz.health.service.IOrderSettingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderSettingController {


    @Resource
    private IOrderSettingService orderSettingService;

    @PutMapping("/orderSetting")
    public Result setOrderSettingByOrderDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.updateOrderSettingByOrderDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.ORDERSETTING_FAIL);
        }
    }


    @GetMapping("/orderSetting/{date}")//2023-10-21
    public Result getOrderSettingListByDate(@PathVariable("date")Date date){//自定义转换器
        List<OrderSettingDTO> orderSettingDTOList = orderSettingService.findOrderSettingListByDate(date);
        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,orderSettingDTOList);
    }
}
