package com.ritz.health.web.controller;

import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.service.ITcmConstitutionIdentificationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin("*")
public class TcmConstitutionIdentificationController {

    @Resource
    private ITcmConstitutionIdentificationService tcmConstitutionIdentificationService;


    @GetMapping("/tcm/{currentPage}/{pageSize}")
    public PageResult selectTcmConstitutionIdentificationListByCondition(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize){
        QueryPageBean queryPageBean = new QueryPageBean();
        queryPageBean.setCurrentPage(currentPage);
        queryPageBean.setPageSize(pageSize);
        PageResult pageResult = tcmConstitutionIdentificationService.queryTcmConstitutionIdentificationListByCondition(queryPageBean);
        return pageResult;
    }
}
