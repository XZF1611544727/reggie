package com.juct.reggie.mapper;

import com.juct.reggie.domain.Dish;
import com.juct.reggie.domain.DishFlavor;
import com.juct.reggie.dto.DishDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-14 14:51
 */
@Mapper
public interface DishMapper {
    void addDish(DishDto dishDto);

    List<Dish> selectByPage(String name);

    @Select("select * from dish where id = #{id}")
    Dish selectById(String id);

    void updateDish(DishDto dishDto);

    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    @Update("update dish set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Long id);
}
