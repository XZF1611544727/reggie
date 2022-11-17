package com.juct.reggie.mapper;

import com.juct.reggie.domain.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:42
 */
@Mapper
public interface SetmealDishMapper {
    void addSetmeal(SetmealDish setmealDish);

    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> selectBySetmealId(Long id);

    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);
}
