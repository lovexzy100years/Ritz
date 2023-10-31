package com.ritz.health.mapper;

import com.ritz.health.vo.SetmealCheckGroupVO;

import java.util.HashMap;
import java.util.List;

public interface ISetmealCheckGroupMapper {

    void addSetmealIdAndCheckGroupId(HashMap<String, Object> map);

    List<SetmealCheckGroupVO> findCheckGroupIdBySetmealId(Integer setmealId);

    void deleteCheckGroupIdBySetmealId(Integer setmealId);
}
