package com.yr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.yr.bean.Menu;
import  com.yr.service.MenuService;
import  com.yr.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author fredom
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2024-11-22 12:58:21
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




