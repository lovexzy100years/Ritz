package com.ritz.health.mapper;

import com.ritz.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICheckItemMapper {
   // @Param 表示告诉mybatis  这是一个单独的参数
    List<CheckItem> findCheckItemListByCondition(@Param("queryString") String queryString);

    void insertCheckItem(CheckItem checkItem);

    void editCheckItem(CheckItem checkItem);

    void updateStatusById(int id);

    List<CheckItem> selectCheckItemList();

    CheckItem queryCheckItemById(Integer id);
}
