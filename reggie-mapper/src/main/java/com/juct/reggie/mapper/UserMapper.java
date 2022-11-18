package com.juct.reggie.mapper;

import com.juct.reggie.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 谢智峰
 * @create 2022-11-18 20:30
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where phone = #{phone}")
    User getUserByPhone(String phone);

    void insert(User user);
}
