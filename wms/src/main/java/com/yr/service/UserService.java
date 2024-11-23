package com.yr.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yr.bean.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listAll();

    Page<User> pageC(Page<User> page);

    Page<User> pageCC(Page<User> page, LambdaQueryWrapper<User> queryWrapper);
}
