package com.ritz.health.service;

import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.pojo.CheckItem;

import java.util.List;

public interface ICheckItemService {

    PageResult queryCheckItemByCondition(QueryPageBean queryPageBean);


    CheckItem addCheckItem(CheckItem checkItem);

    void updateCheckItem(CheckItem checkItem);

    void deleteCheckItemById(int id);

    List<CheckItem> selectCheckItemList();

    CheckItem findCheckItemById(Integer id);
}
