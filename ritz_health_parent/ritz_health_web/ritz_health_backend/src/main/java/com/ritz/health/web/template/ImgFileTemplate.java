package com.ritz.health.web.template;

import com.ritz.health.test.QiniuUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component("img")
public class ImgFileTemplate extends FileTemplateAbstract {


    @Async(value = "getAsyncExecutor")
    @Override
    public void uploadFile(MultipartFile multipartFile) {
        System.out.println("进行图片上传...");
        String filename = multipartFile.getOriginalFilename();
        int pointLastIndexOf = filename.lastIndexOf(".");
        String suffix = filename.substring(pointLastIndexOf);//后缀
        filename = filename.substring(0,pointLastIndexOf) + UUID.randomUUID().toString();
        filename += suffix;
        //setmeal-img-clean-2-1->setmealService处理请求的线程...
        System.out.println("上传的文件为:" + filename);
        try {
            QiniuUtils.upload2Qiniu(multipartFile.getBytes(), filename);//
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("套餐图片上传七牛云成功!!!");


    }
}
