package com.yr.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author fredom
* @description 针对表【goods】的数据库操作Service
* @createDate 2024-11-23 10:19:33
*/
public interface GoodsService extends IService<Goods> {

    Page<Goods> pageCC(Page<Goods> page, LambdaQueryWrapper<Goods> userLambdaQueryWrapper);
}
