package com.juct.reggie.service;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.domain.Category;

/**
 * @author 谢智峰
 * @create 2022-11-13 15:19
 */
public interface CategoryService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Category> selectByPage(Integer page, Integer pageSize);

    /**
     * 分页查询
     * @param category
     */
    void addCategory(Category category);

    /**
     * 更新
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 删除
     */
    void deleteCategory(String id);
}
