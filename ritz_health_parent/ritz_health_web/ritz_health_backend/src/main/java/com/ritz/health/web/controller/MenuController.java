package com.ritz.health.web.controller;

import com.ritz.health.entity.Result;
import com.ritz.health.pojo.Menu;
import com.ritz.health.service.IMenuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @GetMapping("/menu")
    public Result findMenuList(){
        List<Menu> menuList = menuService.queryMenuList();
        return new Result(true, "查询菜单列表成功",menuList);
    }
}
