package com.yr.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Goods;
import com.yr.bean.Record;
import com.yr.bean.RecordRes;
import com.yr.common.QueryPageParam;
import com.yr.common.Result;
import com.yr.service.GoodsService;
import com.yr.service.RecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 叶蕊
 * @ClassName RecordController
 * @description: TODO
 * @date 2024年 11月 23日
 * @version: 1.0
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    @Resource
    private RecordService recordService;
    @Resource
    private GoodsService goodsService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Record record){
        //操作记录表
        boolean b = recordService.save(record);
        Goods goods = goodsService.getById(record.getGoods());
        //操作物品表
        if ("2".equals(record.getAction())){//出库
            goods.setCount(goods.getCount() - record.getCount());
            record.setUserId(-record.getCount());
        }else {//入库
            goods.setCount(goods.getCount() + record.getCount());
        }
        goodsService.updateById(goods);
        if (b)
            return Result.success();
        return Result.fail();
    }
    @PostMapping("/listReturnResult")
    public Result<List<RecordRes>> listReturnResult(@RequestBody QueryPageParam pageParam){

        Page<Record> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());

        HashMap param = pageParam.getParam();
        String name = param.get("name").toString();
        String goodstype = param.get("goodstype").toString();
        String storage = param.get("storage").toString();
        String roleId = param.get("roleId").toString();
        String userId = param.get("userId").toString();

        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        //直接写sql 联表查询
        queryWrapper.apply(" r.goods = g.id AND g.storage = s.id AND g.goodsType = gt.id ");

        if ("2".equals(roleId)){
            queryWrapper.eq("r.user_id",pageParam.getParam().get("userId"));
        }
        //物品名模糊查询
        if (StringUtils.isNotBlank(name)){
            queryWrapper.like("g.name",pageParam.getParam().get("name"));
        }
        //物品分类查询条件
        if (StringUtils.isNotBlank(goodstype)){
            queryWrapper.eq("gt.id",pageParam.getParam().get("goodstype"));
        }
        //仓库查询条件
        if (StringUtils.isNotBlank(storage)){
            queryWrapper.eq("s.id",pageParam.getParam().get("storage"));
        }
        Page<RecordRes> res = recordService.pageCC(page,queryWrapper);
        return  Result.success(res.getRecords(),res.getTotal());
    }
}
