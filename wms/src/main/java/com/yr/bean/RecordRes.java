package com.yr.bean;

import lombok.Data;

/**
 * @author 叶蕊
 * @ClassName RecordRes
 * @description: TODO
 * @date 2024年 11月 23日
 * @version: 1.0
 */
@Data
public class RecordRes extends Record{
    private String username;
    private String adminname;
    private String storagename;
    private String goodsname;
    private String goodstypename;

}
