package com.juct.reggie.service;

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
}
