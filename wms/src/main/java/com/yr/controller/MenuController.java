package com.yr.controller;

import com.yr.bean.Menu;
import com.yr.common.Result;
import com.yr.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 叶蕊
 * @ClassName MenuController
 * @description: TODO
 * @date 2024年 11月 22日
 * @version: 1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestParam String roleId){
        List<Menu> list = (List<Menu>) menuService.lambdaQuery().like(Menu::getMenuright, roleId).list();
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }
}
