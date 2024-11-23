package com.yr.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yr.bean.User;
import org.apache.ibatis.annotations.Param;

/**
* @author fredom
* @description 针对表【storage】的数据库操作Mapper
* @createDate 2024-11-22 22:37:57
* @Entity generator.bean.Storage
*/
public interface StorageMapper extends BaseMapper<Storage> {

    Page<Storage> selectAndPageAndLamada(Page<Storage> page,@Param(Constants.WRAPPER)  LambdaQueryWrapper<Storage> queryWrapper);
}




