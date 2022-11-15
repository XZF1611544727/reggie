package com.juct.reggie.mapper;

import com.juct.reggie.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-13 15:20
 */
@Mapper
public interface CategoryMapper {

    //查询所有
    List<Category> selectAll();

    //新增
    void addCategory(Category category);

    //更新
    void updateCategory(Category category);

    //删除
    void deleteCategory(String id);

    //查询菜品表中分类的数量
    @Select("select count(*) from dish where category_id = #{id}")
    int countDishByCid(String id);

    //查询套餐表中分类的数量
    @Select("select count(*) from setmeal where category_id = #{id}")
    int countSetmealByCid(String id);

    @Select("select * from category where type = #{type} order by sort ASC")
    List<Category> selectByType(String type);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category selectById(Long id);
}
