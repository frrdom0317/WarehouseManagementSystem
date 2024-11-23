package com.yr.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 叶蕊
 * @ClassName User
 * @description: TODO
 * @date 2024年 11月 20日
 * @version: 1.0
 */
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String no;
    private String name;
    private String password;
    private Integer age;
    private Integer sex;
    private Integer roleId;
    private String phone;
    private String isValid;
}
