package com.ritz.health.mapper;

import com.ritz.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISetmealMapper {

    List<Setmeal> findSetmealListByCondition(@Param("queryString") String queryString);

    void updateSetmealStatusById(Integer setmealId);

    void insertSetmeal(Setmeal setmeal);

    Setmeal findSetmealById(Integer setmealId);

    void editSetmeal(Setmeal setmeal);

    List<Setmeal> findSetmeal();
}
