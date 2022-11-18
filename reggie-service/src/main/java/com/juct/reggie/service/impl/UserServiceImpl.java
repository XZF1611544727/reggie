package com.juct.reggie.service.impl;

import cn.hutool.core.util.IdUtil;
import com.juct.reggie.common.exception.BusinessException;
import com.juct.reggie.domain.User;
import com.juct.reggie.mapper.UserMapper;
import com.juct.reggie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.prefs.BackingStoreException;

/**
 * @author 谢智峰
 * @create 2022-11-18 20:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String phone) {
        User user = userMapper.getUserByPhone(phone);
        if (user==null) {
            //注册用户
            user = new User();
            user.setId(IdUtil.getSnowflakeNextId());
            user.setPhone(phone);
            userMapper.insert(user);
        } else {
            if (user.getStatus() == 0) {
                throw new BusinessException(500, "该用户已被禁用");
            }
        }
        return user;
    }
}
