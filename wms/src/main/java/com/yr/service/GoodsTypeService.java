package com.yr.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.GoodsType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author fredom
* @description 针对表【goodstype】的数据库操作Service
* @description 针对表【goodstype】的数据库操作Service
* @createDate 2024-11-23 09:39:22
*/
public interface GoodsTypeService extends IService<GoodsType> {

    Page<GoodsType> pageCC(Page<GoodsType> page, LambdaQueryWrapper<GoodsType> userLambdaQueryWrapper);
}
