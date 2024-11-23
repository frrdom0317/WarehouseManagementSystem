package com.yr.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yr.bean.User;
import com.yr.common.QueryPageParam;
import com.yr.service.UserService;
import com.yr.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

/**
 * @author 叶蕊
 * @ClassName UserController
 * @description: TODO
 * @date 2024年 11月 20日
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    //判断账号是否已经存在
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List<User> list = userService.lambdaQuery().eq(User::getNo, no).list();
        return list.size() > 0 ? Result.success(): Result.fail();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        log.info("user=> {}",user);
        boolean save = userService.save(user);
        if (save)
            return Result.success();
        return Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public Result mod(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b)
            return Result.success();
        return Result.fail();
    }
    //新增或修改 有id就是修改 没有就是新增
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody User user){
        boolean b = userService.saveOrUpdate(user);
        if (b)
            return Result.success();
        return Result.fail();
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        if (id == 1)
            return Result.fail();
        boolean b = userService.removeById(id);
        if (b)
            return Result.success();
        return Result.fail();
    }
    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List<User> list = userService.lambdaQuery().eq(User::getNo, user.getNo()).eq(User::getPassword, user.getPassword()).list();
        return list.size() > 0 ? Result.success(list.get(0)) : Result.fail();
    }
    //查询(模糊 匹配)
//    @PostMapping("/listP")
//    public Result<List<User>> listP(@RequestBody User user){
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
//        if (StringUtils.isNotBlank(user.getName()))
//            lambdaQueryWrapper.eq(User::getName,user.getName());
//        return Result.success(userService.list(lambdaQueryWrapper));
//    }
    //获取用户列表
    @GetMapping("/listP")
    public Result<List<User>> listP(){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User :: getRoleId,2);
        return Result.success(userService.list(userLambdaQueryWrapper));
    }

    //分页查询
    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam pageParam){
        log.info("map==>{}",pageParam);

        Page<User> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User :: getName,pageParam.getParam().get("name"));
        Page<User> res = userService.page(page, userLambdaQueryWrapper);
        log.info("总数===>{}",res.getTotal());
        return  res.getRecords();
    }

    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam pageParam){
        log.info("map==>{}",pageParam);

        Page<User> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.like(User :: getName,pageParam.getParam().get("name"));
//        Page<User> res = userService.pageC(page);

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User :: getName,pageParam.getParam().get("name"));

        Page<User> res = userService.pageCC(page,userLambdaQueryWrapper);

        log.info("总数===>{}",res.getTotal());
        return  res.getRecords();
    }

    @PostMapping("/listReturnResult")
    public Result<List<User>> listReturnResult(@RequestBody QueryPageParam pageParam){
        log.info("map==>{}",pageParam);

        Page<User> page = new Page<>();
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.like(User :: getName,pageParam.getParam().get("name"));
//        Page<User> res = userService.pageC(page);
        HashMap param = pageParam.getParam();
        String name = param.get("name").toString();
        String sex = param.get("sex").toString();
        String roleId = param.get("roleId").toString();
        log.info(roleId);

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //名字
        if (StringUtils.isNotBlank(name)){
            userLambdaQueryWrapper.like(User :: getName,pageParam.getParam().get("name"));
        }
        //性别
        if (StringUtils.isNotBlank(sex)){
            userLambdaQueryWrapper.eq(User :: getSex,pageParam.getParam().get("sex"));
        }
        //角色
        if (StringUtils.isNotBlank(roleId)){
            userLambdaQueryWrapper.eq(User :: getRoleId,pageParam.getParam().get("roleId"));
        }

        Page<User> res = userService.pageCC(page,userLambdaQueryWrapper);
        log.info("总数===>{}",res.getTotal());
        return  Result.success(res.getRecords(),res.getTotal());
    }
}
