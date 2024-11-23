package com.yr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yr.bean.GoodsType;
import com.yr.mapper.GoodsTypeMapper;
import com.yr.service.GoodsTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author fredom
* @description 针对表【goodstype】的数据库操作Service实现
* @createDate 2024-11-23 09:39:22
*/
@Service
public class GoodstTpeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType>
    implements GoodsTypeService {
    @Resource
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public Page<GoodsType> pageCC(Page<GoodsType> page, LambdaQueryWrapper<GoodsType> userLambdaQueryWrapper) {
        return goodsTypeMapper.selectAndPageAndLamada(page,userLambdaQueryWrapper);
    }
}




