package com.juct.reggie.controller;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.R;
import com.juct.reggie.domain.Orders;
import com.juct.reggie.dto.OrdersDto;
import com.juct.reggie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 15:42
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/submit")
    public R submit(
            @RequestBody Orders orders
    ) {
        orderService.submit(orders);
        return R.success(null);
    }

    @GetMapping("/userPage")
    public R<PageInfo<OrdersDto>> userPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        PageInfo<OrdersDto> list = orderService.userPage(page,pageSize);
        log.info("list = {} ", list);
        return R.success(list);
    }

}
