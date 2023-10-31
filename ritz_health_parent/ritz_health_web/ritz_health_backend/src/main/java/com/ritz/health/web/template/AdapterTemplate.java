package com.ritz.health.web.template;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;


@Component
public class AdapterTemplate implements ApplicationContextAware {//spring高级  IOC  bean的初始化  回调函数

    //@Resource
    private HashMap<String,Object> fileTemplateAbstracts = new HashMap<>();

    //应该根据code找到对应的bean 来处理文件上传
    public void executeFile(String code, MultipartFile file){
          //根据 code值  找到对应的bean
        FileTemplateAbstract fileTemplate = (FileTemplateAbstract) fileTemplateAbstracts.get(code);
        fileTemplate.uploadFile(file);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Object bean1 = applicationContext.getBean(TemplateConstant.excelCode);
        Object bean2 = applicationContext.getBean(TemplateConstant.imgCode);
        fileTemplateAbstracts.put(TemplateConstant.excelCode,bean1);
        fileTemplateAbstracts.put(TemplateConstant.imgCode,bean2);
    }
}
