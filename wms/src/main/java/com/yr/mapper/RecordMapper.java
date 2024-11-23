package com.yr.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yr.bean.RecordRes;
import org.apache.ibatis.annotations.Param;

/**
* @author fredom
* @description 针对表【record】的数据库操作Mapper
* @createDate 2024-11-23 14:10:35
* @Entity com.yr.bean.Record
*/
public interface RecordMapper extends BaseMapper<Record> {

    Page<RecordRes> selectAndPageAndLamada(Page<Record> page, @Param(Constants.WRAPPER) QueryWrapper<Record> userLambdaQueryWrapper);
}




