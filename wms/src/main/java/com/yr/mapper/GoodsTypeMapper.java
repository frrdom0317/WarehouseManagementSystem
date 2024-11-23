package com.yr.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author fredom
* @description 针对表【goodstype】的数据库操作Mapper
* @createDate 2024-11-23 09:39:22
* @Entity generator.bean.Goodstype
*/
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    Page<GoodsType> selectAndPageAndLamada(Page<GoodsType> page,@Param(Constants.WRAPPER) LambdaQueryWrapper<GoodsType> userLambdaQueryWrapper);
}




