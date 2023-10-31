package com.ritz.health.web.controller;

import com.ritz.health.dto.CheckGroupDTO;
import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.entity.Result;
import com.ritz.health.message.MessageConstant;
import com.ritz.health.pojo.CheckGroup;
import com.ritz.health.service.ICheckGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CheckGroupController {//class  method

    @Resource
    private ICheckGroupService checkGroupService;

    @GetMapping("/checkGroup/{currentPage}/{pageSize}")
    public PageResult selectCheckGroupByCondition(@PathVariable("currentPage")Integer currentPage, @PathVariable("pageSize")Integer pageSize, String queryString){
        QueryPageBean queryPageBean = new QueryPageBean();
        queryPageBean.setCurrentPage(currentPage);
        queryPageBean.setPageSize(pageSize);
        queryPageBean.setQueryString(queryString);
        PageResult pageResult = checkGroupService.queryCheckGroupByCondition(queryPageBean);
        return pageResult;
    }

    @GetMapping("/checkGroup")
    public Result selectCheckGroupList(){
        List<CheckGroup>  checkGroupList = checkGroupService.queryCheckGroupList();
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
    }
    //根据id查询检查组
    @GetMapping("/checkGroup/{checkGroupId}")
    public Result selectCheckGroupById(@PathVariable("checkGroupId")Integer checkGroupId){
        CheckGroupDTO checkGroupDTO = checkGroupService.queryCheckGroupById(checkGroupId);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupDTO);
    }

    @PostMapping("/checkGroup/{checkItemIds}")
    public Result addCheckGroupByCheckItemIds(@PathVariable("checkItemIds")Integer[] checkItemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.addCheckGroupByCheckItemIds(checkItemIds,checkGroup);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }
    @DeleteMapping("/checkGroup/{checkGroupId}")
    public Result deleteCheckGroupById(@PathVariable("checkGroupId")Integer checkGroupId){
        try {
            checkGroupService.deleteCheckGroupById(checkGroupId);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }



    @PutMapping("/checkGroup/{checkItemIds}")//防刷  黑白名单
    public Result editCheckGroupByCheckItemIds(@PathVariable("checkItemIds")Integer[] checkItemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.editCheckGroupByCheckItemIds(checkItemIds,checkGroup);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }


}
