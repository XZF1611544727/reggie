package com.juct.reggie.common.exception;

import lombok.Getter;

//只需要获取状态码和消息
@Getter
public class BusinessException extends RuntimeException{

    // {code: 500, msg: "系统错误，请联系管理员"}

    //状态码
    private Integer code;

    public BusinessException(Integer code,String message){
        super(message);
        this.code = code;
    }

}
