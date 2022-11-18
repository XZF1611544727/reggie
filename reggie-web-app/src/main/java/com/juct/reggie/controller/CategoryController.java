package com.juct.reggie.controller;

import com.juct.reggie.common.R;
import com.juct.reggie.domain.Category;
import com.juct.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-18 20:51
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public R<List> selectCategory() {
        List<Category> list = categoryService.selectCategory();
        return R.success(list);
    }
}
