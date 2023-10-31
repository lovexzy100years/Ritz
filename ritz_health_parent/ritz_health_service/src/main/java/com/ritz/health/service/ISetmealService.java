package com.ritz.health.service;

import com.ritz.health.dto.SetmealDTO;
import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.entity.Result;
import com.ritz.health.pojo.Setmeal;

public interface ISetmealService {


    PageResult querySetmealByCondition(QueryPageBean queryPageBean);

    void deleteSetmealById(Integer setmealId);

    void addSetmeal(Integer[] checkGroupIds, Setmeal setmeal);

    SetmealDTO querySetmealById(Integer setmealId);

    void editSetmealByCheckGroupIds(Integer[] checkGroupIds, Setmeal setmeal);

    public void uploadImg(byte[] bytes, String fileName);
}
