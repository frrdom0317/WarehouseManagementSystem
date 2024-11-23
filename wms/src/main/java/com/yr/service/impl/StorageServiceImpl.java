package com.yr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yr.bean.Storage;
import com.yr.bean.User;
import com.yr.service.StorageService;
import com.yr.mapper.StorageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author fredom
* @description 针对表【storage】的数据库操作Service实现
* @createDate 2024-11-22 22:37:57
*/
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage>
    implements StorageService{
    @Resource
    StorageMapper storageMapper;
    @Override
    public Page<Storage> pageCC(Page<Storage> page, LambdaQueryWrapper<Storage> queryWrapper) {
        return storageMapper.selectAndPageAndLamada(page,queryWrapper);
    }
}




