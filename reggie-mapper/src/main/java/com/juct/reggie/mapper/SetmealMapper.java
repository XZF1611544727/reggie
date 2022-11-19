package com.juct.reggie.mapper;

import com.juct.reggie.domain.Setmeal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Delete("delete from setmeal where id = #{id}")
    void deleteById(Long id);

    @Update("update setmeal set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Long id);

    @Select("select status from setmeal where id = #{id}")
    int selectStatus(Long id);

    @Select("select * from setmeal where category_id = #{categoryId} and status = #{status}")
    List<Setmeal> selectByCategoryId(Long categoryId, Integer status);
}
