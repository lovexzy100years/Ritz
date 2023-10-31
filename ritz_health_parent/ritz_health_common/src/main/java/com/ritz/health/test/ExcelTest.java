package com.ritz.health.test;

import com.alibaba.excel.EasyExcel;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExcelTest {


    //借助easyExcel对Excel做读的动作
    /*
    File file, Class head, ReadListener readListener
     */
    public static void readExcel(){
        EasyExcel.read(new File("d:\\test.xls"), ExcelDemo.class, new ExcelReadListener()).sheet("页一").doRead();
    }

    //借助easyExcel对Excel做写的动作
    public static void writeExcel(){
        File path = new File("d:\\test.xls");
        if(!path.exists()){
            //不追加数据
            System.out.println("===================>");
            EasyExcel.write(path,ExcelDemo.class).sheet("页一").doWrite(getData());
        }else{
            //追加数据  末尾追加
            System.out.println("------------------->");
            //EasyExcel.write(path,ExcelDemo.class).needHead(false).sheet("页一").doWrite(getData());
            EasyExcel.write("d:\\test.xls",ExcelDemo.class).needHead(false)
                    .withTemplate("d:\\test.xls") //给定一个模版
                    .file("d:\\test2.xls") //将原有模版的数据拷贝过去 并在拷贝的内容之后去实现追加
                    .sheet("页一")
                    .doWrite(getData());
        }

    }

    public static List<ExcelDemo> getData(){
        ArrayList<ExcelDemo> list = new ArrayList<>();
        for (int i = 101; i <= 200; i++) {
            ExcelDemo excelDemo = new ExcelDemo();
            excelDemo.setName("张二狗" + i);
            excelDemo.setAge(i);
            excelDemo.setAddress("桥洞" + i);
            excelDemo.setBirthday(new Date());
            excelDemo.setHobby("女" + i);
            list.add(excelDemo);
        }
        return list;
    }

    public static void main(String[] args) {
        //ExcelTest.readExcel();
        ExcelTest.writeExcel();
        //ExcelTest.appendWrite();
    }
}
