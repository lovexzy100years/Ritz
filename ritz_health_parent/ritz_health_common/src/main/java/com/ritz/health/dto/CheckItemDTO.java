package com.ritz.health.dto;

public class CheckItemDTO {
    private Integer id;//主键
    private String code;//项目编码
    private String name;//项目名称
    private String sex;//适用性别
    private String age;//适用年龄（范围），例如：20-50
    private Float price;//价格
    private String type;//检查项类型，分为检查和检验两种类型
    private String remark;//项目说明
    private String attention;//注意事项
    private Integer status;
    private Integer[] checkItemIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
