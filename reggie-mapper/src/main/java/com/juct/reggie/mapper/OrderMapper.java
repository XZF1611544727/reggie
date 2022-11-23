package com.juct.reggie.mapper;

import com.juct.reggie.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 15:53
 */
@Mapper
public interface OrderMapper {

    @Select("select * from orders where user_id = #{id} order by order_time desc")
    List<Orders> getOrders(Long id);

    void add(Orders orders);
}
