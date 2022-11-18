package com.juct.reggie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.exception.BusinessException;
import com.juct.reggie.domain.Category;
import com.juct.reggie.mapper.CategoryMapper;
import com.juct.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-13 15:20
 */
@Service
public class CategoryMapperImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页查询
     * @param page 当前页
     * @param pageSize 每页容量
     * @return
     */
    @Override
    public PageInfo<Category> selectByPage(Integer page, Integer pageSize) {
        //开启PageHelp
        PageHelper.startPage(page, pageSize);
        //查询数据
        List<Category> list = categoryMapper.selectAll();
        //封装到PageInfo并返回
        return  new PageInfo<>(list);
    }

    /**
     * 新增分类
     * @param category
     */
    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }


    /**
     * 修改分类
     * @param category
     */
    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteCategory(String id) {
        //根据分类ID查询菜品中是否有关联分类
        int dish = categoryMapper.countDishByCid(id);
        //根据分类ID查询套餐中是否有关联分类
        int setmeal = categoryMapper.countSetmealByCid(id);
        if (dish > 0 || setmeal > 0) {
            throw new BusinessException(500,"该分类关联了菜品或套餐不可删除");
        }
        categoryMapper.deleteCategory(id);
    }

    /**
     * 查询菜品分类
     * @param type
     * @return
     */
    @Override
    public List<Category> selectByType(String type) {
        return categoryMapper.selectByType(type);
    }

    @Override
    public List<Category> selectCategory() {
        return categoryMapper.selectAll();
    }

}
