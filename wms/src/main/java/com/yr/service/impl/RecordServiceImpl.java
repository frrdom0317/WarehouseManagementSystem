package com.yr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yr.bean.Record;
import com.yr.bean.RecordRes;
import com.yr.service.RecordService;
import com.yr.mapper.RecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author fredom
* @description 针对表【record】的数据库操作Service实现
* @createDate 2024-11-23 14:10:35
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{
    @Resource
    private RecordMapper recordMapper;

    @Override
    public Page<RecordRes> pageCC(Page<Record> page, QueryWrapper<Record> queryWrapper) {
        return recordMapper.selectAndPageAndLamada(page,queryWrapper);
    }
}




