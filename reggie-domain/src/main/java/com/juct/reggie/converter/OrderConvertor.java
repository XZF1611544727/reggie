package com.juct.reggie.converter;

import com.juct.reggie.domain.Car;
import com.juct.reggie.domain.OrderDetail;
import com.juct.reggie.domain.Orders;
import com.juct.reggie.domain.ShoppingCart;
import com.juct.reggie.dto.OrdersDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 16:00
 */
@Mapper
public interface OrderConvertor {
    OrderConvertor INSTANCE = Mappers.getMapper(OrderConvertor.class);

    OrdersDto toDto(Orders orders);

    Orders toPo(OrdersDto ordersDto);

    List<OrdersDto> toDtoList(List<Orders> orders);

    OrderDetail toOrderDetail(ShoppingCart shoppingCart);
}
