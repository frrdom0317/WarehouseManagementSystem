package com.yr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yr.bean.User;
import com.yr.mapper.UserMapper;
import com.yr.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 叶蕊
 * @ClassName UserServiceImpl
 * @description: TODO
 * @date 2024年 11月 20日
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> listAll() {
        return userMapper.selectAll();
    }

    @Override
    public Page<User> pageC(Page<User> page) {
        return userMapper.selectAndPage(page);
    }

    @Override
    public Page<User> pageCC(Page<User> page, LambdaQueryWrapper<User> queryWrapper) {
        return userMapper.selectAndPageAndLamada(page,queryWrapper);
    }
}
