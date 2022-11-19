package com.juct.reggie.controller;

import com.juct.reggie.common.R;
import com.juct.reggie.dto.SetmealDto;
import com.juct.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-19 22:38
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @GetMapping("/list")
    public R<List<SetmealDto>> selectSetmeal(
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "1") Integer status
    ) {
        List<SetmealDto> list = setmealService.selectByCategoryId(categoryId, status);
        return R.success(list);
    }
}
