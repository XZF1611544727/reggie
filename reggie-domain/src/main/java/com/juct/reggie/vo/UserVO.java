package com.juct.reggie.vo;


import lombok.Data;

//接收登录的手机号和验证码
@Data
public class UserVO {
    private String phone;
    private String code;
}
