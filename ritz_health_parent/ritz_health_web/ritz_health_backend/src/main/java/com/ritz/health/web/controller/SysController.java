package com.ritz.health.web.controller;

import com.ritz.health.pojo.SysLog;
import com.ritz.health.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect //通知类
public class SysController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private ISysLogService sysLogService;

    //切入点表达式
    @Pointcut(value = "execution(* com.ritz.health.web.controller.*.*(..))")
    public void pt1(){

    }


    /*
    private Date visitTime;//开始访问controller的时间
    private String visitTimeStr;//开始访问controller的时间
    private String username;//当前登录人的用户名 zhishikeng
    private String ip;//访问者的主机ip 0:0:0:0:0:0:0:1  本机
    private String url;// controller上方requestMapping 的属性值 + method上方requestMapping的属性值 /user/findAll
    private long executionTime; //执行的时长  执行一个controller所耗费的时间
    private String method; //com.ritz.health.web.controller.CheckItemController.selectCheckItemByCondition
     */

    //环绕通知
    @Around(value = "pt1()")
    public Object around(ProceedingJoinPoint pdj) throws Throwable {
        //1.获取visitTime
        Date visitTime = new Date();
        //2.获取用户名  session
        String username = "zhishikeng";
        //3.获取ip
        String ip = request.getRemoteHost();
        //4.获取访问的url
        String url = null;
        //String url = request.getRequestURI();  // orderSetting/{orderDate}
        //System.out.println(ip + url + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
        //System.out.println("环绕执行了》。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
        Class<?> clazz = pdj.getTarget().getClass();  //因为我们的controller上都没有以及映射地址
        String methodName = pdj.getSignature().getName();
        Object[] args = pdj.getArgs();//具体的参数值
        Class[] argsClazzs = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            //pdj.getArgs() 获取当前拦截的方法执行 所需的参数值
            if(args[i] == null ){
               // args[i] = "";
                argsClazzs[i] = "".getClass();
            }else {
                argsClazzs[i] = args[i].getClass();
            }
        }
        Class mClass = MultipartFile.class;
        for (int i = 0;i < argsClazzs.length;i++) {

            if(argsClazzs[i] == CommonsMultipartFile.class){
                argsClazzs[i] = mClass;
            }
        }
        Method method = clazz.getMethod(methodName, argsClazzs);
        if("GET".equals(request.getMethod())){
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            String[] values = getMapping.value();
            url = values[0];
        }
        if("POST".equals(request.getMethod())){
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            String[] values = postMapping.value();
            url = values[0];
        }
        if("DELETE".equals(request.getMethod())){
            DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
            String[] values = deleteMapping.value();
            url = values[0];
        }
        if("PUT".equals(request.getMethod())){
            PutMapping putMapping = method.getAnnotation(PutMapping.class);
            String[] values = putMapping.value();
            url = values[0];
        }
        //当前拦截的切入点 还有保证切入点的原样执行
        Object result = pdj.proceed(args);//切入点 uploadFile

        //5.执行时长
        long executionTime = new Date().getTime() - visitTime.getTime();
        //6.method = com.ritz.health.web.controller.CheckItemController.selectCheckItemByCondition
        String md = clazz.getName() + "." + method.getName();
        //组装sysLog的数据 调用service完成插入
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setIp(ip);
        sysLog.setUsername(username);
        sysLog.setUrl(url);
        sysLog.setExecutionTime(executionTime);
        sysLog.setMethod(md);

        sysLogService.insertSyslog(sysLog);

        return result;
    }
}
