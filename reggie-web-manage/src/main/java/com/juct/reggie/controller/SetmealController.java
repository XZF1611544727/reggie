package com.juct.reggie.controller;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.R;
import com.juct.reggie.common.exception.BusinessException;
import com.juct.reggie.domain.Setmeal;
import com.juct.reggie.dto.SetmealDto;
import com.juct.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:34
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R addSetmeal(@RequestBody SetmealDto setmealDto) {
        //验证前端数据
        if (setmealService.byName(setmealDto.getName()) != null) {
            return R.error("姓名已存在");
        }
        setmealService.addSetmeal(setmealDto);
        return R.success(null);
    }

    /**
     * 分页获取套餐
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<PageInfo<SetmealDto>> selectByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            String name
    ) {
        PageInfo<SetmealDto> pageInfo = setmealService.selectByPage(page, pageSize, name);
        return R.success(pageInfo);
    }

    @GetMapping("/{id}")
    public R<SetmealDto> selectById(
            @PathVariR.error("数据不
        SetmealDto setmealDto = setmealService.selemeal(setmealDto);
        return R.success(null