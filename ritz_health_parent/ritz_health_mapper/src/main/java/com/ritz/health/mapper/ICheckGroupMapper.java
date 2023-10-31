package com.ritz.health.mapper;

import com.ritz.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICheckGroupMapper {

    List<CheckGroup> findCheckGroupListByCondition(@Param("queryString") String queryString);

    void addCheckGroup(CheckGroup checkGroup);

    void deleteCheckGroupById(Integer checkGroupId);

    CheckGroup findCheckGroupById(Integer checkGroupId);

    void editCheckGroup(CheckGroup checkGroup);

    List<CheckGroup> findCheckGroupList();
}
