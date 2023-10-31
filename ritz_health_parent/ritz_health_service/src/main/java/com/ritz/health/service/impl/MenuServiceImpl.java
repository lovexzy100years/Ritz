package com.ritz.health.service.impl;

import com.ritz.health.mapper.IMenuMapper;
import com.ritz.health.pojo.Menu;
import com.ritz.health.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private IMenuMapper menuMapper;

    @Override
    public List<Menu> queryMenuList() {
        //1.查询所有的menu信息
        List<Menu> menuAll = menuMapper.selectMenuList();
        //2.组装一级菜单信息
        List<Menu> menuOneAll = new ArrayList<>();
        for (Menu menu : menuAll) {
            if(menu.getParentMenuId() == null){
                menuOneAll.add(menu);
            }
        }
        //3.对一级菜单排序
        Collections.sort(menuOneAll);

        //4.设置子节点
        for (Menu menu : menuOneAll) {//一级
            //根据当前节点的id查询子节点
            List<Menu> childrenMenuList = getChildren(menu.getId(),menuAll);
            //当前节点设置子节点
            menu.setChildren(childrenMenuList);
        }

        return menuOneAll;
    }

    private List<Menu> getChildren(Integer id, List<Menu> menuAll) {
        //二级
        List<Menu> menuTwoAll = new ArrayList<>();
        for (Menu menu : menuAll) {
            if(menu.getParentMenuId() == id){
                menuTwoAll.add(menu);
            }
        }
        //多级
        for (Menu menu : menuTwoAll) {
            menu.setChildren(getChildren(menu.getId(),menuAll));
        }
        //排序
        Collections.sort(menuTwoAll);
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (menuTwoAll.size() == 0) {
            return new ArrayList<Menu>();
        }
        return menuTwoAll;
    }
}
