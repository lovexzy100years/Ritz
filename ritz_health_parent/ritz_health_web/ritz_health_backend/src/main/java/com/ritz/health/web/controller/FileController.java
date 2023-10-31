package com.ritz.health.web.controller;

import com.alibaba.excel.EasyExcel;
import com.ritz.health.entity.Result;
import com.ritz.health.excel.OrderSettingExcel;
import com.ritz.health.message.MessageConstant;
import com.ritz.health.service.ISetmealService;
import com.ritz.health.web.listener.OrderSettingExcelListener;
import com.ritz.health.web.template.AdapterTemplate;
import com.ritz.health.web.template.ExcelFileTemplate;
import com.ritz.health.web.template.ImgFileTemplate;
import com.ritz.health.web.template.TemplateConstant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class FileController {//TODO 是否需要设计模式

    @Resource
    private AdapterTemplate adapterTemplate;

    //当前这个借口主要目的是用于处理客户端文件上传的一个接口
    //代码冗余很严重
    //扩展性不强

    //七牛云 文件上传(图片 视频)

    //@Resource
    //private ISetmealService setmealService;

    //@Resource
    //private OrderSettingExcelListener orderSettingExcelListener;


    /*@PostMapping("/setmeal/uploadImg")
    public Result uploadSetmealImg(@RequestBody MultipartFile imgFile){
        //http-bio-80-exec-1->setmealController处理请求的线程...
        System.out.println(Thread.currentThread().getName()+"->setmealController处理请求的线程...");
        //1.获取文件的真实名称
        String filename = imgFile.getOriginalFilename();
        int pointLastIndexOf = filename.lastIndexOf(".");
        String suffix = filename.substring(pointLastIndexOf);//后缀
        filename = filename.substring(0,pointLastIndexOf) + UUID.randomUUID().toString();
        filename += suffix;
        try {
            setmealService.uploadImg(imgFile.getBytes(),filename);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,"http://s2pv81px9.hn-bkt.clouddn.com/" + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }*/


   /* @PostMapping("/orderSetting/uploadExcel")
    public Result uploadOrderSettingExcel(@RequestBody MultipartFile excelFile){
        //获取文件的内容
        try {
            InputStream inputStream = excelFile.getInputStream();
            EasyExcel.read(inputStream, OrderSettingExcel.class,orderSettingExcelListener).sheet().doRead();
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

    }*/

    @PostMapping("/upload/{code}")
    public Result uploadFile(@PathVariable("code")String code,@RequestBody MultipartFile file){
        //只需要拿到file然后处理即可
        try {
            adapterTemplate.executeFile(code,file);
            return new Result(true,"文件上传成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"文件上传失败!!!");
        }

    }

}
