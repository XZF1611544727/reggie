package com.juct.reggie.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.converter.OrderConvertor;
import com.juct.reggie.domain.AddressBook;
import com.juct.reggie.domain.OrderDetail;
import com.juct.reggie.domain.Orders;
import com.juct.reggie.domain.ShoppingCart;
import com.juct.reggie.dto.OrdersDto;
import com.juct.reggie.mapper.AddressBookMapper;
import com.juct.reggie.mapper.OrderDetailMapper;
import com.juct.reggie.mapper.OrderMapper;
import com.juct.reggie.mapper.ShoppingCartMapper;
import com.juct.reggie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 15:52
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    AddressBookMapper addressBookMapper;

    @Transactional
    @Override
    public void submit(Orders orders) {
        Long id = (Long) ThreadLocalUtil.get();
        orders.setId(IdUtil.getSnowflakeNextId());
        orders.setNumber(IdUtil.getSnowflakeNextIdStr());
        orders.setUserId(id);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setAmount(BigDecimal.ONE);
        AddressBook addressBook = addressBookMapper.selectBookById(orders.getAddressBookId());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getDetail());
        orders.setConsignee(addressBook.getConsignee());
        orders.setStatus(2);
        log.info("orders = {} ", orders);
        orderMapper.add(orders);
        List<ShoppingCart> list = shoppingCartMapper.list(id);
        for (ShoppingCart shoppingCart : list) {
            OrderDetail orderDetail = OrderConvertor.INSTANCE.toOrderDetail(shoppingCart);
            orderDetail.setId(IdUtil.getSnowflakeNextId());
            orderDetail.setOrderId(orders.getId());
            orderDetail.setAmount(BigDecimal.ONE);
            orderDetailMapper.add(orderDetail);
        }
        shoppingCartMapper.clean(id);
    }

    @Override
    public PageInfo<OrdersDto> userPage(Integer page,Integer pageSize) {
        Long id = (Long) ThreadLocalUtil.get();
        PageHelper.startPage(page, pageSize);
        List<Orders> list =orderMapper.getOrders(id);
        PageInfo pageInfo = new PageInfo(list);
        List<OrdersDto> ordersDtoList = new ArrayList<>();
        for (Orders orders : list) {
            OrdersDto ordersDto = OrderConvertor.INSTANCE.toDto(orders);
            List<OrderDetail> detailList = orderDetailMapper.getDetail(orders.getId());
            ordersDto.setOrderDetails(detailList);
            ordersDtoList.add(ordersDto);
        }
        pageInfo.setList(ordersDtoList);
        return pageInfo;
    }
}
