package com.juct.reggie.service.impl;

import cn.hutool.core.util.IdUtil;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.converter.SetmealConverter;
import com.juct.reggie.domain.Employee;
import com.juct.reggie.domain.Setmeal;
import com.juct.reggie.domain.SetmealDish;
import com.juct.reggie.dto.SetmealDto;
import com.juct.reggie.mapper.SetmealDishMapper;
import com.juct.reggie.mapper.SetmealMapper;
import com.juct.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:38
 */
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    public void addSetmeal(SetmealDto setmealDto) {
        Setmeal setmeal = SetmealConverter.INSTANCE.toPO(setmealDto);
        setmeal.setId(IdUtil.getSnowflakeNextId());
        Employee emp = (Employee) ThreadLocalUtil.get();
        setmeal.setCreateUser(emp.getId());
        setmeal.setUpdateUser(emp.getId());
        setmealMapper.addSetmeal(setmeal);

        List<SetmealDish> list = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : list) {
            setmealDish.setId(IdUtil.getSnowflakeNextId());
            setmealDish.setCreateUser(emp.getId());
            setmealDish.setUpdateUser(emp.getId());
            setmealDishMapper.addSetmeal(setmealDish);
        }
    }
}
