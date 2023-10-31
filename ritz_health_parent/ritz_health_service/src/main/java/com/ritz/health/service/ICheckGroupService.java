package com.ritz.health.service;

import com.ritz.health.dto.CheckGroupDTO;
import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.pojo.CheckGroup;

import java.util.List;

public interface ICheckGroupService {

    PageResult queryCheckGroupByCondition(QueryPageBean queryPageBean);

    void addCheckGroupByCheckItemIds(Integer[] checkItemIds, CheckGroup checkGroup);

    void deleteCheckGroupById(Integer checkGroupId);

    CheckGroupDTO queryCheckGroupById(Integer checkGroupId);

    void editCheckGroupByCheckItemIds(Integer[] checkItemIds, CheckGroup checkGroup);

    List<CheckGroup> queryCheckGroupList();
}
