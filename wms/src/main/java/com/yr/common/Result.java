package com.yr.common;

import lombok.Data;

/**
 * @author 叶蕊
 * @ClassName Result
 * @description: 封装返回给客户端的数据
 * @date 2024年 11月 20日
 * @version: 1.0
 */
@Data
public class Result <T>{
    private Integer code;//状态码 200 400
    private String msg;//状态描述符 成功 失败
    private Long total;//总记录数
    private T data;//数据

    public static Result success(){
        return result(200,"成功",0L,null);
    }
    public static<T> Result<T> success(T data){
        return result(200,"成功",0L,data);
    }
    public static<T> Result<T> success(T data,Long total){
        return result(200,"成功",total,data);
    }
    public static Result fail(){
        return result(400,"失败",0L,null);
    }
    private static<T> Result<T> result(Integer code,String msg,Long total,T data){
        Result<T> res = new Result<>();
        res.setData(data);
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        return res;
    }
}
