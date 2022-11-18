package com.juct.reggie.service;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.domain.Setmeal;
import com.juct.reggie.dto.SetmealDto;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:38
 */
public interface SetmealService {
    /**
     * 新增套餐
     * @param setmealDto
     */
    void addSetmeal(SetmealDto setmealDto);

    /**
     * 条件分页查询
     * @param page
     * @param pageSize
     * @param name
     */
    PageInfo<SetmealDto> selectByPage(Integer page, Integer pageSize, String name);

    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    Setmeal byName(String name);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    SetmealDto selectById(Long id);

    /**
     * 更新数据
     * @param setmealDto
     */
    void updateSetmeal(SetmealDto setmealDto);

    /**
     * 删除
     * @param ids
     */
    void deleteSetmeals(Long[] ids);

    /**
     * 更新售卖状态
     * @param status
     * @param ids
     */
    void updateStatus(Integer status, Long[] ids);

    boolean selectStatus(Long[] ids);
}
