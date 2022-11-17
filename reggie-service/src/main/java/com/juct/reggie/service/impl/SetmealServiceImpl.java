package com.juct.reggie.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.exception.BusinessException;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.converter.SetmealConverter;
import com.juct.reggie.domain.Category;
import com.juct.reggie.domain.Employee;
import com.juct.reggie.domain.Setmeal;
import com.juct.reggie.domain.SetmealDish;
import com.juct.reggie.dto.SetmealDto;
import com.juct.reggie.mapper.CategoryMapper;
import com.juct.reggie.mapper.SetmealDishMapper;
import com.juct.reggie.mapper.SetmealMapper;
import com.juct.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
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

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增数据
     * @param setmealDto
     */
    @Transactional
    @Override
    public void addSetmeal(SetmealDto setmealDto) {
        Setmeal setmeal = SetmealConverter.INSTANCE.toPO(setmealDto);
        long setmealId = IdUtil.getSnowflakeNextId();
        setmeal.setId(setmealId);
        Employee emp = (Employee) ThreadLocalUtil.get();
        setmeal.setCreateUser(emp.getId());
        setmeal.setUpdateUser(emp.getId());
        setmealMapper.addSetmeal(setmeal);
        addSetmealDish(setmealDto);
    }

    /**
     * 条件分页查询
     * @param page
     * @param pageSize
     * @param name
     */
    @Override
    public PageInfo<SetmealDto> selectByPage(Integer page, Integer pageSize, String name) {
        //开启分页查询
        PageHelper.startPage(page,pageSize);
        //查询数据
        if(StrUtil.isNotBlank(name)){
            name = "%"+name+"%";
        }
        List<Setmeal> list = setmealMapper.selectByName(name);
        //封装数据
        PageInfo pageInfo = new PageInfo(list);
        //把List<Setmeal>转换为List<SetmealDto>
        List<SetmealDto> setmealDtos = SetmealConverter.INSTANCE.toDtoList(list);
        //查询关联套餐分类
        for (SetmealDto setmealDto : setmealDtos) {
            //根据套餐分类ID查询套餐分类名称
            Category category = categoryMapper.selectById(setmealDto.getCategoryId());
            setmealDto.setCategoryName(category.getName());
        }
        //设置分页结果
        pageInfo.setList(setmealDtos);
        return pageInfo;

    }

    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    @Override
    public Setmeal byName(String name) {
        return setmealMapper.byName(name);
    }

    @Override
    public SetmealDto selectById(Long id) {
        Setmeal setmeal = setmealMapper.selectById(id);
        SetmealDto setmealDto = SetmealConverter.INSTANCE.toDTO(setmeal);
        setmealDto.setSetmealDishes(setmealDishMapper.selectBySetmealId(id));
        return setmealDto;
    }

    @Transactional
    @Override
    public void updateSetmeal(SetmealDto setmealDto) {
        //拷贝对象属性
        Setmeal setmeal = SetmealConverter.INSTANCE.toPO(setmealDto);
        //获取当前用户对象
        Employee emp = (Employee) ThreadLocalUtil.get();
        //补全更新用户信息时间信息
        setmeal.setUpdateUser(emp.getId());
        setmeal.setUpdateTime(LocalDateTime.now());
        //执行更新操作
        setmealMapper.updateSetmeal(setmeal);
        //删除关联套餐菜品信息
        setmealDishMapper.deleteBySetmealId(setmeal.getId());
        //新增套餐关联菜品
        addSetmealDish(setmealDto);
    }

    public void addSetmealDish(SetmealDto setmealDto) {
        List<SetmealDish> list = setmealDto.getSetmealDishes();
        Employee emp = (Employee) ThreadLocalUtil.get();
        for (SetmealDish setmealDish : list) {
            setmealDish.setId(IdUtil.getSnowflakeNextId());
            setmealDish.setSetmealId(setmealDto.getId());
            setmealDish.setCreateUser(emp.getId());
            setmealDish.setUpdateUser(emp.getId());
            setmealDishMapper.addSetmeal(setmealDish);
        }
    }
}
