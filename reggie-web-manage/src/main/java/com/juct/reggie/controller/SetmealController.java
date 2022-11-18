package com.juct.reggie.controller;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.R;
import com.juct.reggie.common.exception.BusinessException;
import com.juct.reggie.domain.Setmeal;
import com.juct.reggie.dto.SetmealDto;
import com.juct.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 谢智峰
 * @create 2022-11-15 20:34
 */
@Slf4j
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

    /**
     * 根据ID查询套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<SetmealDto> selectById(
            @PathVariable Long id
    ) {
        if (id == null) {
            return R.error("数据不完整");
        }
        SetmealDto setmealDto = setmealService.selectById(id);
        return R.success(setmealDto);
    }

    /**
     * 修改套餐
     * @param setmealDto
     * @return
     */
    @PutMapping
    public R updateSetmeal(
            @RequestBody SetmealDto setmealDto
    ) {
        setmealService.updateSetmeal(setmealDto);
        return R.success(null);
    }

    /**
     * 根据ID删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public R deleteSetmeals(Long[] ids) {
        //先检查套餐是否停售
        if (!setmealService.selectStatus(ids)) {
            return R.error("套餐未停售");
        }
        setmealService.deleteSetmeals(ids);
        return R.success(null);
    }

    /**
     * 更新售卖状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R updateStatus(
            @PathVariable Integer status,
            Long[] ids
    ) {
        setmealService.updateStatus(status, ids);
        return R.success(null);
    }
}
