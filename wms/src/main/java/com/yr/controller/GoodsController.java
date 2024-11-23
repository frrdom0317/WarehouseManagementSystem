package com.yr.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Goods;
import com.yr.common.QueryPageParam;
import com.yr.common.Result;
import com.yr.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 叶蕊
 * @ClassName GoodsController
 * @description: TODO
 * @date 2024年 11月 23日
 * @version: 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    //新增或修改 有id就是修改 没有就是新增
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody Goods goods){
        boolean b = goodsService.saveOrUpdate(goods);
        if (b)
            return Result.success();
        return Result.fail();
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        if (id == 1)
            return Result.fail();
        boolean b = goodsService.removeById(id);
        if (b)
            return Result.success();
        return Result.fail();
    }
    @PostMapping("/listReturnResult")
    public Result<List<Goods>> listReturnResult(@RequestBody QueryPageParam pageParam){

        Page<Goods> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());

        HashMap param = pageParam.getParam();
        String name = param.get("name").toString();
        String goodstype = param.get("goodstype").toString();
        String storage = param.get("storage").toString();

        LambdaQueryWrapper<Goods> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //名字
        if (StringUtils.isNotBlank(name)){
            userLambdaQueryWrapper.like(Goods :: getName,pageParam.getParam().get("name"));
        }
        //物品分类
        if (StringUtils.isNotBlank(goodstype)){
            userLambdaQueryWrapper.eq(Goods :: getGoodstype,pageParam.getParam().get("goodstype"));
        }
        //仓库
        if (StringUtils.isNotBlank(storage)){
            userLambdaQueryWrapper.eq(Goods :: getStorage,pageParam.getParam().get("storage"));
        }
        Page<Goods> res = goodsService.pageCC(page,userLambdaQueryWrapper);
        return  Result.success(res.getRecords(),res.getTotal());
    }
}
