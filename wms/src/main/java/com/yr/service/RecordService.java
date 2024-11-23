package com.yr.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yr.bean.RecordRes;

/**
* @author fredom
* @description 针对表【record】的数据库操作Service
* @createDate 2024-11-23 14:10:35
*/
public interface RecordService extends IService<Record> {

    Page<RecordRes> pageCC(Page<Record> page, QueryWrapper<Record> queryWrapper);
}
