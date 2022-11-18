package com.juct.reggie.service;

import com.juct.reggie.domain.User;

/**
 * @author 谢智峰
 * @create 2022-11-18 20:23
 */
public interface UserService {
    User checkUser(String phone);
}
