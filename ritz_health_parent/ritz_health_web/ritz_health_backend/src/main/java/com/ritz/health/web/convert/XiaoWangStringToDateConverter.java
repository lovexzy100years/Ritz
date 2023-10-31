package com.ritz.health.web.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XiaoWangStringToDateConverter implements Converter<String, Date> {

    /**
     * @param source 原本的数据类型 前端传递的数据类型 自动传递
     * @return
     */
    @Override
    public Date convert(String source) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa--->");
        if (source != null && source.length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
        return null;
    }
}