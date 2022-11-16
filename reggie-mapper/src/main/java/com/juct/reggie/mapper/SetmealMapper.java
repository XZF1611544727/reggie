package com.juct.reggie.mapper;

import com.juct.reggie.domain.Setmeal;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:39
 */
@Mapper
public interface SetmealMapper {
    void addSetmeal(Setmeal setmeal);
}
