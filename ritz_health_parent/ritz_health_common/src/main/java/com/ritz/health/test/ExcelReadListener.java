package com.ritz.health.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelReadListener extends AnalysisEventListener<ExcelDemo> {

    private List<ExcelDemo> excelDemoList = new ArrayList<>();

    //每解析一行就会执行
    @Override
    public void invoke(ExcelDemo data, AnalysisContext context) {
        excelDemoList.add(data);
    }

    //解析完毕 之后需要做的事情
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println(excelDemoList);
    }




}
