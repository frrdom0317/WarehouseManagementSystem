package com.yr.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Menu;
import com.yr.bean.Storage;
import com.yr.bean.User;
import com.yr.common.QueryPageParam;
import com.yr.common.Result;
import com.yr.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 叶蕊
 * @ClassName StorageController
 * @description: TODO
 * @date 2024年 11月 22日
 * @version: 1.0
 */
@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;
    //新增或修改 有id就是修改 没有就是新增
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody Storage storage){
        boolean b = storageService.saveOrUpdate(storage);
        if (b)
            return Result.success();
        return Result.fail();
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        if (id == 1)
            return Result.fail();
        boolean b = storageService.removeById(id);
        if (b)
            return Result.success();
        return Result.fail();
    }
    @PostMapping("/listReturnResult")
    public Result<List<Storage>> listReturnResult(@RequestBody QueryPageParam pageParam){

        Page<Storage> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());

        HashMap param = pageParam.getParam();
        String name = param.get("name").toString();

        LambdaQueryWrapper<Storage> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //名字
        if (StringUtils.isNotBlank(name)){
            userLambdaQueryWrapper.like(Storage :: getName,pageParam.getParam().get("name"));
        }
        Page<Storage> res = storageService.pageCC(page,userLambdaQueryWrapper);
        return  Result.success(res.getRecords(),res.getTotal());
    }
    @GetMapping("/list")
    public Result list(){
        List<Storage> list =  storageService.list();
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }
}
