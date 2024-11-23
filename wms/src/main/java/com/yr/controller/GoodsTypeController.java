package com.yr.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.GoodsType;
import com.yr.common.QueryPageParam;
import com.yr.common.Result;
import com.yr.service.GoodsTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 叶蕊
 * @ClassName GoodsTypeController
 * @description: TODO
 * @date 2024年 11月 23日
 * @version: 1.0
 */
@RestController
@RequestMapping("/goodstype")
public class GoodsTypeController {
    @Resource
    private GoodsTypeService goodsTypeService;
    //新增或修改 有id就是修改 没有就是新增
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody GoodsType goodsType){
        boolean b = goodsTypeService.saveOrUpdate(goodsType);
        if (b)
            return Result.success();
        return Result.fail();
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        if (id == 1)
            return Result.fail();
        boolean b = goodsTypeService.removeById(id);
        if (b)
            return Result.success();
        return Result.fail();
    }
    @PostMapping("/listReturnResult")
    public Result<List<GoodsType>> listReturnResult(@RequestBody QueryPageParam pageParam){

        Page<GoodsType> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());

        HashMap param = pageParam.getParam();
        String name = param.get("name").toString();

        LambdaQueryWrapper<GoodsType> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //名字
        if (StringUtils.isNotBlank(name)){
            userLambdaQueryWrapper.like(GoodsType :: getName,pageParam.getParam().get("name"));
        }
        Page<GoodsType> res = goodsTypeService.pageCC(page,userLambdaQueryWrapper);
        return  Result.success(res.getRecords(),res.getTotal());
    }
    @GetMapping("/list")
    public Result list(){
        List<GoodsType> list =  goodsTypeService.list();
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }
}
