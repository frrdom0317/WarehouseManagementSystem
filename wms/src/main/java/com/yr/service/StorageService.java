package com.yr.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.Storage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yr.bean.User;

/**
* @author fredom
* @description 针对表【storage】的数据库操作Service
* @createDate 2024-11-22 22:37:57
*/
public interface StorageService extends IService<Storage> {
    Page<Storage> pageCC(Page<Storage> page, LambdaQueryWrapper<Storage> queryWrapper);
}
