package com.juct.reggie.mapper;

import com.juct.reggie.domain.DishFlavor;
import com.juct.reggie.dto.DishDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-14 14:51
 */
@Mapper
public interface DishFlavorMapper {
    void addDishFlavorMapper(DishFlavor dishFlavor);

    @Select("select * from dish_flavor where dish_id = #{id}")
    List<DishFlavor> selectByDishId(String id);

    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteByDishId(Long id);
}
