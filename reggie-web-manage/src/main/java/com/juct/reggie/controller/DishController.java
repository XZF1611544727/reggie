package com.juct.reggie.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.R;
import com.juct.reggie.domain.Dish;
import com.juct.reggie.domain.DishFlavor;
import com.juct.reggie.dto.DishDto;
import com.juct.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-14 14:04
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 获取分页请求
     * @param page 当前页
     * @param pageSize 每页容量
     * @param name 条件
     * @return
     */
    @GetMapping("/page")
    public R<PageInfo<DishDto>> getPage(
            //接受参数并设置默认值
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name
            ) {
        return R.success(dishService.selectByPage(page,pageSize,name));
    }

    /**
     * 添加菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R addDish(
            //接收前端封装数据
            @RequestBody DishDto dishDto
    ) {
        dishService.addDish(dishDto);
        return R.success(null);
    }

    /**
     * 根据ID 获取菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> selectById(
            @PathVariable String id
    ) {
        //验证数据完整性
        if (StrUtil.isBlank(id)) {
            return R.error("数据不全！");
        }
        return R.success(dishService.selectById(id));
    }

    /**
     * 更新菜品
     * @param dishDto
     * @return
     */
    @PutMapping
    public R updateDish(
            @RequestBody DishDto dishDto
    ) {
        dishService.updateDish(dishDto);
        return R.success(null);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public R deletesDish( Long[] ids) {
        log.info("ids = {} ", ids);
        dishService.deleteDish(ids);
        return R.success(null);
    }

    /**
     * 批量禁用
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R updateStatus(
           @PathVariable Integer status,
            Long[] ids
    ) {
        dishService.updateStatus(status,ids);
        return R.success(null);
    }
}
