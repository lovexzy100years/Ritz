package com.ritz.health.web.task;

import com.ritz.health.mapper.ISetmealMapper;
import com.ritz.health.pojo.Setmeal;
import com.ritz.health.test.QiniuUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Component
public class SpringTaskCleanImg {

    //注入setmealMapper查询数据库中所有的套餐链接地址
    @Resource
    private ISetmealMapper setmealMapper;

    /*
    cron 时间表达式  用于指定当前定时任务执行的时机
     */
    @Scheduled(cron = "0 0/2 * ? * *")//每天凌晨执行
    //@Scheduled(cron = "* * * * * ?")//每天凌晨执行
    //@Async //异步执行  异步  不阻塞
    @Async(value = "getAsyncExecutor")
    public void cleanImg(){
        System.out.println(Thread.currentThread().getName() + "--->线程的名称");
        System.out.println(Thread.currentThread().getClass() + "--->线程的类型");
        System.out.println("定时器开始清理cloud垃圾图片...");
        //根据差集做清理
        //System.out.println("haha。。。。。。。。。。。。。。。。。。。。。");
        List<Setmeal> setmealList = setmealMapper.findSetmeal();
        //创建set集合装载数据库查询的套餐图片链接
        HashSet<String> imgListDB = new HashSet<>();
        for (Setmeal setmeal : setmealList) {
            imgListDB.add(setmeal.getImg());
        }
        //获取云存储上指定空间的图片列表
        HashSet<String> imgListCloud = QiniuUtils.findImgList();
        //获取差集
        Set<String> differenceSet = new HashSet<String>(imgListCloud);
        differenceSet.removeAll(imgListDB);
        System.out.println(differenceSet + "----------------------->差集部分");//域名
        for (String imgName : differenceSet) {
            imgName = imgName.substring(imgName.lastIndexOf("/") + 1);
            System.out.println(imgName + "---------------------->要被清除的文件");
            QiniuUtils.deleteFileFromQiniu(imgName);
        }
        System.out.println("定时器清理cloud垃圾图片完成...");
    }
}
