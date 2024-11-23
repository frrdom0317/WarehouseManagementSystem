package com.yr.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();

    Page<User> selectAndPage(Page<User> page);

    Page<User> selectAndPageAndLamada(Page<User> page,@Param(Constants.WRAPPER) LambdaQueryWrapper<User> queryWrapper);
}
