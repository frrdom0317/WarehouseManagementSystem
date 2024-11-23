package com.yr.common;

import lombok.Data;

import java.util.HashMap;

/**
 * @author 叶蕊
 * @ClassName QueryPageParam
 * @description: 客户端传递的参数的封装
 * @date 2024年 11月 20日
 * @version: 1.0
 */
@Data
public class QueryPageParam {
    private static int PAGE_SIZE = 10;
    private static int PAGE_NUM = 1;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;
    private HashMap param = new HashMap();

    {
        param.put("name","");
        param.put("sex","");
    }
}
