package com.ritz.health.mapper;

import com.ritz.health.vo.CheckGroupCheckItemVO;

import java.util.HashMap;
import java.util.List;

public interface ICheckGroupCheckItemMapper {
    void addCheckItemIdAndCheckGroupId(CheckGroupCheckItemVO vo);

    void addCheckItemIdAndCheckGroupId(HashMap<String,Object> map);

    void deleteCheckGroupCheckItemByCheckGroupId(Integer checkGroupId);

    List<CheckGroupCheckItemVO> findCheckItemIdsByCheckGroupId(Integer checkGroupId);
}
