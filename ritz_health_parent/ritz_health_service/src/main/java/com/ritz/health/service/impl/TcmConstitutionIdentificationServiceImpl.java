package com.ritz.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.mapper.ITcmConstitutionIdentificationMapper;
import com.ritz.health.pojo.TcmConstitutionIdentification;
import com.ritz.health.service.ITcmConstitutionIdentificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TcmConstitutionIdentificationServiceImpl implements ITcmConstitutionIdentificationService {

    @Resource
    private ITcmConstitutionIdentificationMapper tcmConstitutionIdentificationMapper;

    @Override
    public PageResult queryTcmConstitutionIdentificationListByCondition(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        if(currentPage <= 0 || currentPage == null){
            currentPage = 1;
        }
        Integer pageSize = queryPageBean.getPageSize();
        if(pageSize <= 0 || pageSize == null){
            pageSize = 1;
        }
        Page<Object> page = PageHelper.startPage(currentPage, pageSize);
        List<TcmConstitutionIdentification> tcmConstitutionIdentificationList = tcmConstitutionIdentificationMapper.findTcmConstitutionIdentificationListByCondition();
        PageResult pageResult = new PageResult(page.getTotal(), tcmConstitutionIdentificationList);
        return pageResult;
    }
}
