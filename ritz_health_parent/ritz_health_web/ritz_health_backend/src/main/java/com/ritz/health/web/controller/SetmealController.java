package com.ritz.health.web.controller;


import com.ritz.health.dto.CheckGroupDTO;
import com.ritz.health.dto.SetmealDTO;
import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.entity.Result;
import com.ritz.health.message.MessageConstant;
import com.ritz.health.pojo.CheckGroup;
import com.ritz.health.pojo.Setmeal;
import com.ritz.health.service.ISetmealService;
import com.ritz.health.test.QiniuUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class SetmealController {

    @Resource
    private ISetmealService setmealService;

    @GetMapping("/setmeal/{currentPage}/{pageSize}")
    public PageResult selectSetmealByCondition(@PathVariable("currentPage")Integer currentPage, @PathVariable("pageSize")Integer pageSize, String queryString){
        QueryPageBean queryPageBean = new QueryPageBean();
        queryPageBean.setCurrentPage(currentPage);
        queryPageBean.setPageSize(pageSize);
        queryPageBean.setQueryString(queryString);
        PageResult pageResult = setmealService.querySetmealByCondition(queryPageBean);
        return pageResult;
    }

    @DeleteMapping("/setmeal/{setmealId}")
    public Result deleteSetmealById(@PathVariable("setmealId")Integer setmealId){
        try {
            setmealService.deleteSetmealById(setmealId);
            return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }

    //图片上传
    /*@PostMapping("/setmeal/uploadImg")
    public Result uploadSetmealImg(@RequestBody MultipartFile imgFile){
        //1.获取文件的真实名称
        String filename = imgFile.getOriginalFilename();
        int pointLastIndexOf = filename.lastIndexOf(".");
        String suffix = filename.substring(pointLastIndexOf);//后缀
        filename = filename.substring(0,pointLastIndexOf) + UUID.randomUUID().toString();
        filename += suffix;
        System.out.println("上传的文件为:" + filename);
        //2.采用七牛云进行上传
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);
            System.out.println("套餐图片上传七牛云成功!!!");
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,"http://s2pv81px9.hn-bkt.clouddn.com/" + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }*/



    //套餐添加
    @PostMapping("/setmeal/{checkGroupIds}")
    public Result addSetmeal(@PathVariable("checkGroupIds")Integer[] checkGroupIds, @RequestBody Setmeal setmeal){
        try {
            setmealService.addSetmeal(checkGroupIds,setmeal);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }

    }
    //策略模式 模版方法  文件  img  excel

    //套餐修改
    //根据id查询检查组
    @GetMapping("/setmeal/{setmealId}")
    public Result selectSetmealById(@PathVariable("setmealId")Integer setmealId){
        SetmealDTO setmealDTO = setmealService.querySetmealById(setmealId);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmealDTO);
    }

    @PutMapping("/setmeal/{checkGroupIds}")
    public Result editSetmealByCheckGroupIds(@PathVariable("checkGroupIds")Integer[] checkGroupIds, @RequestBody Setmeal setmeal){
        try {
            setmealService.editSetmealByCheckGroupIds(checkGroupIds,setmeal);
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }
}
