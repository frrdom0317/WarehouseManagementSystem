package com.yr.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author fredom
* @description 针对表【goods】的数据库操作Mapper
* @createDate 2024-11-23 10:19:33
* @Entity com.yr.bean.Goods
*/
public interface GoodsMapper extends BaseMapper<Goods> {

    Page<Goods> selectAndPageAndLamada(Page<Goods> page,@Param(Constants.WRAPPER) LambdaQueryWrapper<Goods> userLambdaQueryWrapper);
}




