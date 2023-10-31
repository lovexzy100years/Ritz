package com.ritz.health.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import java.util.Date;

@HeadRowHeight(12)//表头行高
@ContentRowHeight(12)//内容行高
public class ExcelDemo {

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private int age;

    @ExcelProperty("居住地")
    private String address;

    @ExcelProperty("出生日期")
    private Date birthday;

    @ExcelProperty("爱好")
    private String hobby;

    public ExcelDemo() {

    }

    public ExcelDemo(String name, int age, String address, Date birthday, String hobby) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "ExcelDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
