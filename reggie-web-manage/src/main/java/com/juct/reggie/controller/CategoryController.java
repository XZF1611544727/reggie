package com.juct.reggie.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.R;
import com.juct.reggie.common.exception.BusinessException;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.constant.EmployeeConstant;
import com.juct.reggie.domain.Category;
import com.juct.reggie.domain.Employee;
import com.juct.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-13 15:18
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据类型查询分类
     * @param type 1菜品分类 2套餐分类
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> selectByType(
            @RequestParam String type
    ) {
        if (StrUtil.isBlank(type)) {
            return R.error("数据不完整");
        }
        List<Category> list = categoryService.selectByType(type);
        return R.success(list);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<PageInfo<Category>> selectByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        return R.success(categoryService.selectByPage(page, pageSize));
    }

    /**
     * 新增
     * @param category
     * @param session
     * @return
     */
    @PostMapping
    public R addCategory(
            @RequestBody Category category,
            HttpSession session
    ) {
        //验证数据完整性
        String name = category.getName();
        Integer sort = category.getSort();
        Integer type = category.getType();
        if (StrUtil.isBlank(name) || StrUtil.isBlank(String.valueOf(sort)) || StrUtil.isBlank(String.valueOf(type))) {
            return R.error("数据不完整");
        }
        //获取创建用户
        Employee emp = (Employee) session.getAttribute(EmployeeConstant.SESSION);
        //补全数据
        category.setId(IdUtil.getSnowflakeNextId());
        category.setCreateUser(emp.getId());
        category.setUpdateUser(emp.getId());
        log.info("category = {} ", category);
        categoryService.addCategory(category);
        return R.success(null);
    }

    /**
     * 更新
     * @param category
     * @return
     */
    @PutMapping
    public R updateCategory(
            @RequestBody Category category
    ) {
        Employee emp = (Employee) ThreadLocalUtil.get();
        category.setUpdateUser(emp.getId());
        category.setUpdateTime(LocalDateTime.now());
        categoryService.updateCategory(category);
        return R.success(null);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping
    public R deleteCategory(
            @RequestParam String id
    ) {
        if (id.equals(null)) {
            return R.error("缺少数据");
        }
        categoryService.deleteCategory(id);
        return R.success(null);
    }
}
