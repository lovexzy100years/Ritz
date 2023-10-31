package com.ritz.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ritz.health.dto.CheckGroupDTO;
import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.mapper.ICheckGroupCheckItemMapper;
import com.ritz.health.mapper.ICheckGroupMapper;
import com.ritz.health.pojo.CheckGroup;
import com.ritz.health.pojo.CheckItem;
import com.ritz.health.service.ICheckGroupService;
import com.ritz.health.vo.CheckGroupCheckItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class CheckGroupServiceImpl implements ICheckGroupService {

    @Resource
    private ICheckGroupMapper checkGroupMapper;

    @Resource
    private ICheckGroupCheckItemMapper checkGroupCheckItemMapper;

    @Override
    public PageResult queryCheckGroupByCondition(QueryPageBean queryPageBean) {
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
        List<CheckGroup> checkGroupList = null;
        if(queryString == null || queryString.length() <= 0 || "null".equals(queryString)){
            page = PageHelper.startPage(currentPage, pageSize);
            //select * from t_checkItem
            checkGroupList = checkGroupMapper.findCheckGroupList();
        }else{
            page = PageHelper.startPage(currentPage, pageSize);
            //select * from t_checkItem
            checkGroupList = checkGroupMapper.findCheckGroupListByCondition("%" + queryString + "%");
        }
        //3.组装pageResult
        PageResult pageResult = new PageResult(page.getTotal(),checkGroupList);
        return pageResult;
    }

    @Override
    public void addCheckGroupByCheckItemIds(Integer[] checkItemIds, CheckGroup checkGroup) {
        //添加检查组 并且维护检查组与检查项的关系
        //需要获取新增的id
        checkGroupMapper.addCheckGroup(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        //遍历checkItemIds 遍历一次插入一次
        for (Integer checkItemId : checkItemIds) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("checkGroup_Id",checkGroupId);
            map.put("checkItem_Id",checkItemId);
            //CheckGroupCheckItemVO vo = new CheckGroupCheckItemVO();
            //vo.setCheckGroupId(checkGroupId);
            //vo.setCheckItemId(checkItemId);
            //方法的参数是 checkGroupId  与 checkItemId
            //checkGroupCheckItemMapper.addCheckItemIdAndCheckGroupId(vo);
            // no no no
            //checkGroupCheckItemMapper.addCheckItemIdAndCheckGroupId(checkGroupId,checkItemId);

            checkGroupCheckItemMapper.addCheckItemIdAndCheckGroupId(map);
        }
    }

    @Override
    public void deleteCheckGroupById(Integer checkGroupId) {
        //先删除中间表
        checkGroupCheckItemMapper.deleteCheckGroupCheckItemByCheckGroupId(checkGroupId);
        //再删除检查组
        checkGroupMapper.deleteCheckGroupById(checkGroupId);
        //delete from t_checkGroup where id = #{id}
    }

    @Override
    public CheckGroupDTO queryCheckGroupById(Integer checkGroupId) {
        CheckGroup checkGroup = checkGroupMapper.findCheckGroupById(checkGroupId);
        List<CheckGroupCheckItemVO> checkGroupCheckItemVOList = checkGroupCheckItemMapper.findCheckItemIdsByCheckGroupId(checkGroupId);
        CheckGroupDTO checkGroupDTO = new CheckGroupDTO();
        BeanUtils.copyProperties(checkGroup,checkGroupDTO);//属性拷贝

        List<Integer> checkItemIdsList = new ArrayList<>();
        for (CheckGroupCheckItemVO checkGroupCheckItemVO : checkGroupCheckItemVOList) {
            checkItemIdsList.add(checkGroupCheckItemVO.getCheckItemId());
        }
        Integer[] checkItemIdsArray = new Integer[checkItemIdsList.size()];
        checkGroupDTO.setCheckItemIds(checkItemIdsList.toArray(checkItemIdsArray));

        return checkGroupDTO;
    }

    @Override
    public void editCheckGroupByCheckItemIds(Integer[] checkItemIds, CheckGroup checkGroup) {
        Integer checkGroupId = checkGroup.getId();
        //先删除中间表
        checkGroupCheckItemMapper.deleteCheckGroupCheckItemByCheckGroupId(checkGroupId);
        checkGroupMapper.editCheckGroup(checkGroup);
        //遍历checkItemIds 遍历一次插入一次
        for (Integer checkItemId : checkItemIds) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("checkGroup_Id",checkGroupId);
            map.put("checkItem_Id",checkItemId);
            //CheckGroupCheckItemVO vo = new CheckGroupCheckItemVO();
            //vo.setCheckGroupId(checkGroupId);
            //vo.setCheckItemId(checkItemId);
            //方法的参数是 checkGroupId  与 checkItemId
            //checkGroupCheckItemMapper.addCheckItemIdAndCheckGroupId(vo);
            // no no no
            //checkGroupCheckItemMapper.addCheckItemIdAndCheckGroupId(checkGroupId,checkItemId);
            checkGroupCheckItemMapper.addCheckItemIdAndCheckGroupId(map);
        }
    }

    @Override
    public List<CheckGroup> queryCheckGroupList() {
        return checkGroupMapper.findCheckGroupList();
    }
}
