package com.ritz.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ritz.health.dto.SetmealDTO;
import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.entity.Result;
import com.ritz.health.mapper.ISetmealCheckGroupMapper;
import com.ritz.health.mapper.ISetmealMapper;
import com.ritz.health.message.MessageConstant;
import com.ritz.health.pojo.CheckGroup;
import com.ritz.health.pojo.Setmeal;
import com.ritz.health.service.ISetmealService;
import com.ritz.health.test.QiniuUtils;
import com.ritz.health.vo.SetmealCheckGroupVO;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SetmealServiceImpl implements ISetmealService {

    @Resource
    private ISetmealMapper setmealMapper;

    @Resource
    private ISetmealCheckGroupMapper setmealCheckGroupMapper;

    @Override
    public PageResult querySetmealByCondition(QueryPageBean queryPageBean) {

        //1.需要校验我们的分页参数是否正确
        Integer currentPage = queryPageBean.getCurrentPage();
        if(currentPage < 0 || currentPage == null){
            currentPage = 1;
        }
        Integer pageSize = queryPageBean.getPageSize();
        if(pageSize < 0 || pageSize == null){
            pageSize = 5;
        }
        String queryString = queryPageBean.getQueryString();
        //2.构建分页环境
        //PageHelper必须写在真正发起查询的第一行
        Page page = null;
        List<Setmeal> setmealList = null;
        if(queryString == null || queryString.length() <= 0 || "null".equals(queryString)){
            page = PageHelper.startPage(currentPage, pageSize);
            //select * from t_checkItem
            setmealList = setmealMapper.findSetmeal();
        }else{
            page = PageHelper.startPage(currentPage, pageSize);
            //select * from t_checkItem
            setmealList = setmealMapper.findSetmealListByCondition("%" + queryString + "%");
        }
        //3.组装pageResult
        PageResult pageResult = new PageResult(page.getTotal(),setmealList);
        return pageResult;
    }


    @Override
    public void deleteSetmealById(Integer setmealId) {
        setmealMapper.updateSetmealStatusById(setmealId);
    }

    @Override
    public void addSetmeal(Integer[] checkGroupIds, Setmeal setmeal) {
        //插入setmeal
        setmeal.setStatus(1);
        setmealMapper.insertSetmeal(setmeal);
        Integer setmealId = setmeal.getId();
        //维护中间表
        for (Integer checkGroupId : checkGroupIds) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("setmealId",setmealId);
            map.put("checkGroupId",checkGroupId);
            setmealCheckGroupMapper.addSetmealIdAndCheckGroupId(map);
        }
    }

    @Override
    public SetmealDTO querySetmealById(Integer setmealId) {
        Setmeal setmeal = setmealMapper.findSetmealById(setmealId);
        SetmealDTO setmealDTO = new SetmealDTO();
        BeanUtils.copyProperties(setmeal,setmealDTO);
        List<SetmealCheckGroupVO> setmealCheckGroupVOList = setmealCheckGroupMapper.findCheckGroupIdBySetmealId(setmealId);
        ArrayList<Integer> checkGroupListIds = new ArrayList<>();
        for (SetmealCheckGroupVO setmealCheckGroupVO : setmealCheckGroupVOList) {
            System.out.println("=========================");
            checkGroupListIds.add(setmealCheckGroupVO.getCheckGroupId());
        }
        Integer[] checkGroupArrayIds = new Integer[checkGroupListIds.size()];
        setmealDTO.setCheckGroupIds(checkGroupListIds.toArray(checkGroupArrayIds));
        return setmealDTO;
    }

    @Override
    public void editSetmealByCheckGroupIds(Integer[] checkGroupIds, Setmeal setmeal) {
        Integer setmealId = setmeal.getId();
        setmealMapper.editSetmeal(setmeal);
        setmealCheckGroupMapper.deleteCheckGroupIdBySetmealId(setmealId);
        for (Integer checkGroupId : checkGroupIds) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("setmealId",setmealId);
            map.put("checkGroupId",checkGroupId);
            setmealCheckGroupMapper.addSetmealIdAndCheckGroupId(map);//error 保存到数据库  info debug
        }
    }

    @Async(value = "getAsyncExecutor")
    public void uploadImg(byte[] bytes, String fileName){
        System.out.println(Thread.currentThread().getName()+"->setmealService处理请求的线程...");
        //setmeal-img-clean-2-1->setmealService处理请求的线程...
        System.out.println("上传的文件为:" + fileName);
        QiniuUtils.upload2Qiniu(bytes,fileName);//
        System.out.println("套餐图片上传七牛云成功!!!");
    }
}
