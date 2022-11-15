package com.juct.reggie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.constant.EmployeeConstant;
import com.juct.reggie.domain.Category;
import com.juct.reggie.domain.Dish;
import com.juct.reggie.domain.DishFlavor;
import com.juct.reggie.domain.Employee;
import com.juct.reggie.dto.DishDto;
import com.juct.reggie.mapper.CategoryMapper;
import com.juct.reggie.mapper.DishFlavorMapper;
import com.juct.reggie.mapper.DishMapper;
import com.juct.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-14 14:31
 */
@Service
@Transactional
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增菜品
     * @param dishDto
     */
    @Override
    public void addDish(DishDto dishDto) {
        //补全信息
        //补全ID
        long dishId = IdUtil.getSnowflakeNextId();
        dishDto.setId(dishId);
        Employee emp = (Employee) ThreadLocalUtil.get();
        //补全创建修改用户
        dishDto.setCreateUser(emp.getId());
        dishDto.setUpdateUser(emp.getId());
        dishMapper.addDish(dishDto);

        //添加口味
        addFlavors(dishDto);

    }

    /**
     * 分页查询
     * @param page 当前页
     * @param pageSize 每页容量
     * @param name 条件
     * @return
     */
    @Override
    public PageInfo<DishDto> selectByPage(Integer page, Integer pageSize, String name) {
        if (!StrUtil.isBlank(name)) {
            //如果有条件拼接通配符
            name = "%" + name + "name";
        }
        //启动PageHelper分页插件
        PageHelper.startPage(page, pageSize);
        //查询dish
        List<Dish> list = dishMapper.selectByPage(name);
        //封装成PageHelper
        PageInfo pageInfo = new PageInfo(list);
        //新建一个返回给前端的DishDto实体类集合
        ArrayList<DishDto> dtoList = new ArrayList<>();
        for (Dish dis:list) {
            //创建dto对象
            DishDto dishDto = new DishDto();
            //复制对象属性
            BeanUtil.copyProperties(dis, dishDto);
            //获取对应分类菜品对象添加到dto对象中
            Category category = categoryMapper.selectById(dis.getCategoryId());
            dishDto.setCategoryName(category.getName());
            //添加到集合中
            dtoList.add(dishDto);
        }
        //封装分页返回结果
        pageInfo.setList(dtoList);
        return pageInfo;
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public DishDto selectById(String id) {
        Dish dish = dishMapper.selectById(id);
        List<DishFlavor> list = dishFlavorMapper.selectByDishId(id);
        DishDto dishDto = new DishDto();
        BeanUtil.copyProperties(dish, dishDto);
        dishDto.setFlavors(list);
        return dishDto;
    }

    /**
     * 修改菜品
     * @param dishDto
     */
    @Override
    public void updateDish(DishDto dishDto) {
        //补全修改信息
        //补全修改用户及修改时间
        Employee emp = (Employee) ThreadLocalUtil.get();
        dishDto.setUpdateTime(LocalDateTime.now());
        dishDto.setUpdateUser(emp.getId());
        dishMapper.updateDish(dishDto);
        //先根据Dish_id删除口味信息
        dishFlavorMapper.deleteByDishId(dishDto.getId());
        //新增口味信息
        addFlavors(dishDto);
    }

    /**
     * 删除菜品
     * @param ids
     */
    @Override
    public void deleteDish(Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            dishMapper.deleteById(ids[i]);
            dishFlavorMapper.deleteByDishId(ids[i]);
        }
    }

    /**
     * 批量停售起售
     * @param status
     * @param ids
     */
    @Override
    public void updateStatus(Integer status, Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            dishMapper.updateStatus(status,ids[i]);
        }
    }

    /**
     * 新增口味信息
     * @param dishDto
     */
    public void addFlavors(DishDto dishDto) {
        Employee emp = (Employee) ThreadLocalUtil.get();
        //补全口味信息
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor dishFlavor:flavors) {
            //生成口味ID
            dishFlavor.setId(IdUtil.getSnowflakeNextId());
            dishFlavor.setDishId(dishDto.getId());
            dishFlavor.setUpdateUser(emp.getId());
            dishFlavor.setCreateUser(emp.getId());
            //非空判断
            boolean equals = StrUtil.equals(dishFlavor.getValue(), "[]");
            boolean blank = StrUtil.isBlank(dishFlavor.getName());
            if (equals || blank) {
                continue;
            }
            dishFlavorMapper.addDishFlavorMapper(dishFlavor);
        }
    }
}
