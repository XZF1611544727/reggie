package com.juct.reggie.service;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.domain.Dish;
import com.juct.reggie.dto.DishDto;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-14 14:30
 */
public interface DishService {

    /**
     * 根据分类ID查询
     * @param categoryId
     * @return
     */
    List<Dish> selectByCategoryId(String categoryId);

    /**
     * 新增菜品
     * @param dishDto
     */
    void addDish(DishDto dishDto);

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    PageInfo<DishDto> selectByPage(Integer page, Integer pageSize,String name);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    DishDto selectById(Long id);

    /**
     * 根据ID修改
     * @param dishDto
     */
    void updateDish(DishDto dishDto);

    /**
     * 批量删除
     * @param ids
     */
    void deleteDish(Long[] ids);

    /**
     * 批量停售
     * @param status
     * @param ids
     */
    void updateStatus(Integer status, Long[] ids);

    /**
     * 查询是否有关联套餐
     * @param ids
     * @return 有则为true
     */
    boolean selectBySetmealCount(Long[] ids);

    /**
     * 查询指定分类下的指定状态的菜品列表
     * @param categoryId
     * @param status
     * @return
     */
    List<DishDto> listDishWithFlavors(Long categoryId, Integer status);
}
