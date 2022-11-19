package com.juct.reggie.controller;

import com.juct.reggie.common.R;
import com.juct.reggie.domain.Dish;
import com.juct.reggie.dto.DishDto;
import com.juct.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-19 22:21
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

    @Autowired
    DishService dishService;

    @GetMapping("/list")
    public R<List<DishDto>> listDishWithFlavors(
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "1") Integer status
    ) {
        List<DishDto> dishList = dishService.listDishWithFlavors(categoryId, status);
        return R.success(dishList);
    }
}
