package com.ritz.health.web.template;

import org.springframework.web.multipart.MultipartFile;

//文件上传的模版类
public abstract class FileTemplateAbstract {

    //模版方法
    public void templateMethod(){
    }

    //基本方法 就是将来所有实现类都会用得到功能 公共的内容
    public void specificMethod(){
        System.out.println("进行文件上传———————————————————————————————————————》");
    }

    // 钩子方法，子类可根据实际情况选择是否要试下该方法
    public void hookMethod() {
    }

    //抽象方法(可以写多个)
    public abstract void uploadFile(MultipartFile multipartFile);

}
