package com.ritz.health.service.impl;

import com.ritz.health.dto.OrderSettingDTO;
import com.ritz.health.excel.OrderSettingExcel;
import com.ritz.health.mapper.IOrderSettingMapper;
import com.ritz.health.pojo.OrderSetting;
import com.ritz.health.service.IOrderSettingService;
import com.ritz.health.service.therad.BatchInsertOrderSettingExcelRunnalbe;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderSettingServiceImpl implements IOrderSettingService {

    @Resource
    private IOrderSettingMapper orderSettingMapper;

    @Override
    public void insertOrderSetting(OrderSettingExcel orderSettingExcel) {
        OrderSetting orderSetting = new OrderSetting();
        BeanUtils.copyProperties(orderSettingExcel,orderSetting);
        orderSettingMapper.insertOrderSetting(orderSetting);
    }

    @Override
    public void updateOrderSettingByOrderDate(OrderSetting orderSetting) {
        //Date String orderDateStr = year + "-" + month + "-" + day;
        Date orderDate = orderSetting.getOrderDate();
        //系统时间
        Calendar calendar = Calendar.getInstance();
        //更新前端提交的时间
        calendar.setTime(orderDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String orderDateStr = year+"-"+month+"-"+day;
        //根据日期查询当天日期是否已经进行预约设置
        OrderSetting orderSettingDB = orderSettingMapper.findOrderSettingByOrderDate(orderDateStr);
        if(orderSettingDB != null){
            //走修改
            HashMap<String, Object> map = new HashMap<>();
            map.put("orderDate",orderDateStr);
            map.put("number",orderSetting.getNumber());
            orderSettingMapper.updateOrderSettingByOrderDate(map);
        }else{
            //走插入
            orderSettingMapper.insertOrderSetting(orderSetting);
        }

    }

    @Override
    public List<OrderSettingDTO> findOrderSettingListByDate(Date date) {
        //组装开始日期  结束日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //1.获取开始时间
        int startYear = calendar.get(Calendar.YEAR);
        int startMonth = calendar.get(Calendar.MONTH) + 1;
        int startDay = calendar.get(Calendar.DAY_OF_MONTH);
        String startDate = startYear + "-" + startMonth  + "-" + startDay;
        //2.获取结束时间
        //calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH) + 2);
        //int endYear = calendar.get(Calendar.YEAR);
        //int endMonth = calendar.get(Calendar.MONTH);
        int endDay = 31;
        String endDate = startYear + "-" + startMonth + "-" +endDay;
        System.out.println(startDate + "@@@@@@@@@@@@");
        System.out.println(endDate + "@@@@@@@@@@@@");
        //mapper中传map
        HashMap<String, Object> dateMap = new HashMap<>();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        List<OrderSetting> orderSettingList = orderSettingMapper.findOrderSettingListByDate(dateMap);
        //3.装载数据到OrderSettingDTO
        ArrayList<OrderSettingDTO> orderSettingDTOList = new ArrayList<>();
        for (OrderSetting orderSetting : orderSettingList) {
            OrderSettingDTO orderSettingDTO = new OrderSettingDTO();
            orderSettingDTO.setNumber(orderSetting.getNumber());
            orderSettingDTO.setReservations(orderSetting.getReservations());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(orderSetting.getOrderDate());
            int day_of_month = calendar2.get(Calendar.DAY_OF_MONTH);
            orderSettingDTO.setDate(day_of_month);
            orderSettingDTOList.add(orderSettingDTO);
        }
        return orderSettingDTOList;
    }

    @Override
    public void batchInsertOrderSetting(List<OrderSettingExcel> orderSettingExcelList) {//0  main
        //定义批次处理的数量
        int count = 500;
        //统计需要插入的总条数
        int total = orderSettingExcelList.size();
        //计算需要的线程数量
        int threadSize = (total / count) <= 0 ? 1 : (total / count);
        //创建线程池  单一线程池  不是并行的
        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);//20线程
        //创建集合去存储每次批量插入的数据
        List<OrderSettingExcel> batchOrderSettingExcelList = null;
        //创建并发工具类
        //CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(threadSize);

        //begin.countDown();//表示将构造中的参数值减1   如果这个值等于0  下面的任务则可以继续执行

        //遍历线程数去工作
        for (int i = 0; i < threadSize; i++) {
            //1       1
            if((i + 1) == threadSize){
                int startIndex = (i * count);
                int endIndex = total;
                batchOrderSettingExcelList = orderSettingExcelList.subList(startIndex,endIndex);
            }else{
                int startIndex = (i * count);
                int endIndex = (i + 1) * count;
                batchOrderSettingExcelList = orderSettingExcelList.subList(startIndex,endIndex);
            }

            //借助线程提交批量插入的任务
            executorService.execute(new BatchInsertOrderSettingExcelRunnalbe(batchOrderSettingExcelList,end,orderSettingMapper));
            //executorService.submit();
            //executorService.shutdown();
            //executorService.shutdownNow();
        }
        try {
            end.await();//只要所有的线程没有执行完毕任务 那么当然这个service的业务方法就不会结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("批量插入完成  ---》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        //销毁线程池
        executorService.shutdown();
    }
}
