package com.juct.reggie.service;

import com.github.pagehelper.PageInfo;
import com.juct.reggie.domain.Orders;
import com.juct.reggie.dto.OrdersDto;

/**
 * @author 谢智峰
 * @create 2022-11-23 15:51
 */
public interface OrderService {
    void submit(Orders orders);

    PageInfo<OrdersDto> userPage(Integer page, Integer pageSize);
}
