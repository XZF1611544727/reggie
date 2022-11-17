package com.juct.reggie.mapper;

import com.juct.reggie.domain.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:39
 */
@Mapper
public interface SetmealMapper {
    void addSetmeal(Setmeal setmeal);

    @Select("select * from setmeal where name=#{name}")
    Setmeal byName(String name);

    //@Select("select * from setmeal order by create_time desc")
    List<Setmeal> selectByName(String name);

    @Select("select * from setmeal where id = #{id}")
    Setmeal selectById(Long id);

    void updateSetmeal(Setmeal setmeal);
}
