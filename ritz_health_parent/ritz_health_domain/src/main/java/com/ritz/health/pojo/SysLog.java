package com.ritz.health.pojo;

import java.util.Date;

public class SysLog {

    private Date visitTime;//开始访问controller的时间
    private String visitTimeStr;//开始访问controller的时间
    private String username;//当前登录人的用户名 zhishikeng
    private String ip;//访问者的主机ip 0:0:0:0:0:0:0:1  本机
    private String url;// controller上方requestMapping 的属性值 + method上方requestMapping的属性值 /user/findAll
    private long executionTime; //执行的时长  执行一个controller所耗费的时间
    private String method; //com.ritz.health.web.controller.CheckItemController.selectCheckItemByCondition


    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
