package com.juct.reggie.mapper;

import com.juct.reggie.domain.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:42
 */
@Mapper
public interface SetmealDishMapper {
    void addSetmeal(SetmealDish setmealDish);
}
