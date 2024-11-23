package com.yr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yr.bean.Goods;
import com.yr.service.GoodsService;
import com.yr.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author fredom
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2024-11-23 10:19:33
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Page<Goods> pageCC(Page<Goods> page, LambdaQueryWrapper<Goods> userLambdaQueryWrapper) {
        return goodsMapper.selectAndPageAndLamada(page,userLambdaQueryWrapper);
    }
}




