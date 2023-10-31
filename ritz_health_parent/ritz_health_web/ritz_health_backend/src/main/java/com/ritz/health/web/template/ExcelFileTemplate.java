package com.ritz.health.web.template;

import com.alibaba.excel.EasyExcel;
import com.ritz.health.excel.OrderSettingExcel;
import com.ritz.health.web.listener.OrderSettingExcelListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Component("excel")
public class ExcelFileTemplate extends FileTemplateAbstract{

    @Resource
    private OrderSettingExcelListener orderSettingExcelListener;


    @Override
    public void uploadFile(MultipartFile multipartFile) {
        System.out.println("进行Excel上传...");
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EasyExcel.read(inputStream, OrderSettingExcel.class,orderSettingExcelListener).sheet().doRead();
    }
}
