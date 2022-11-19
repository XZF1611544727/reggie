package com.juct.reggie.mapper;

import com.juct.reggie.domain.Dish;
import com.juct.reggie.domain.DishFlavor;
import com.juct.reggie.dto.DishDto;
import lombok.extern.slf4j.Slf4j;
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

    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> selectByCategoryId(String categoryId);

    void addDish(DishDto dishDto);

    List<Dish> selectByPage(String name);

    @Select("select * from dish where id = #{id}")
    Dish selectById(Long id);

    void updateDish(DishDto dishDto);

    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    @Update("update dish set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Long id);

    @Select("select status from dish where id = #{dishId}")
    boolean selectStatusById(Long dishID);

    @Select("select * from dish where category_id = #{categoryId} and status = #{status}")
    List<Dish> listByCidAndStatus(Long categoryId, Integer status);

}
