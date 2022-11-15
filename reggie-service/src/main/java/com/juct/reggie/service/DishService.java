package com.juct.reggie.service;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.domain.Dish;
import com.juct.reggie.dto.DishDto;

/**
 * @author 谢智峰
 * @create 2022-11-14 14:30
 */
public interface DishService {

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
    DishDto selectById(String id);

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
}
