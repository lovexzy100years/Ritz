package com.ritz.health.web.controller;

import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.entity.Result;
import com.ritz.health.message.MessageConstant;
import com.ritz.health.pojo.CheckItem;
import com.ritz.health.service.ICheckItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


//@RequestMapping("/")
@RestController
@CrossOrigin("*")
public class CheckItemController {

    @Resource
    private ICheckItemService checkItemServiceImpl;


    @GetMapping("/checkItem/{id}")
    public Result selectCheckItemById(@PathVariable("id")Integer id){
        CheckItem checkItem = checkItemServiceImpl.findCheckItemById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    @PutMapping("/checkItem")
    public Result updateCheckItem(@RequestBody CheckItem checkItem){
        try {
            checkItemServiceImpl.updateCheckItem(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }

    }//"null"

    @GetMapping("/checkItem/{currentPage}/{pageSize}")
    public PageResult selectCheckItemByCondition(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize,String queryString){
        QueryPageBean queryPageBean = new QueryPageBean();//"null"
        queryPageBean.setCurrentPage(currentPage);
        queryPageBean.setPageSize(pageSize);
        queryPageBean.setQueryString(queryString);
        PageResult pageResult = checkItemServiceImpl.queryCheckItemByCondition(queryPageBean);
        return pageResult;
    }


    @GetMapping("/checkItem")
    public Result selectCheckItemList(){
        List<CheckItem> checkItemList = checkItemServiceImpl.selectCheckItemList();
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemList);
    }



    @PostMapping("/checkItem")
    public Result addCheckItem(@RequestBody CheckItem checkItem){
        try {
            CheckItem checkItemDB = checkItemServiceImpl.addCheckItem(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS,checkItemDB);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    @DeleteMapping("/checkItem/{id}")
    public Result deleteCheckItemById(@PathVariable("id") Integer id){
        try {
            checkItemServiceImpl.deleteCheckItemById(id);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }


}
