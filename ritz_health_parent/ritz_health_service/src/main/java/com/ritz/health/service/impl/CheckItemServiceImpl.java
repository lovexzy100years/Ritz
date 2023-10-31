package com.ritz.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;
import com.ritz.health.mapper.ICheckItemMapper;
import com.ritz.health.pojo.CheckItem;
import com.ritz.health.service.ICheckItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CheckItemServiceImpl  implements ICheckItemService{

    @Resource
    private ICheckItemMapper checkItemMapper;

    @Override
    public PageResult queryCheckItemByCondition(QueryPageBean queryPageBean) {

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
        List<CheckItem> checkItemList = null;
        if(queryString == null || queryString.length() <= 0 || "null".equals(queryString)){//" "
            page = PageHelper.startPage(currentPage, pageSize);
            //select * from t_checkItem
            checkItemList = checkItemMapper.selectCheckItemList();
        }else{
            page = PageHelper.startPage(currentPage, pageSize);
            //select * from t_checkItem
            checkItemList = checkItemMapper.findCheckItemListByCondition("%" + queryString + "%");
        }
        //3.组装pageResult
        PageResult pageResult = new PageResult(page.getTotal(),checkItemList);
        return pageResult;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @Override
    public CheckItem addCheckItem(CheckItem checkItem) {
        checkItem.setStatus(1);
        checkItemMapper.insertCheckItem(checkItem);
        return checkItem;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @Override
    public void updateCheckItem(CheckItem checkItem) {
        checkItemMapper.editCheckItem(checkItem);
    }

    @Override
    public void deleteCheckItemById(int id) {
        checkItemMapper.updateStatusById(id);
    }

    @Override
    public List<CheckItem> selectCheckItemList() {
        return checkItemMapper.selectCheckItemList();
    }

    @Override
    public CheckItem findCheckItemById(Integer id) {
        return checkItemMapper.queryCheckItemById(id);
    }
}
