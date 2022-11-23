package com.juct.reggie.mapper;

import com.juct.reggie.domain.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 15:54
 */
@Mapper
public interface OrderDetailMapper {

    @Select("select * from order_detail where order_id = #{id}")
    List<OrderDetail> getDetail(Long id);

    void add(OrderDetail orderDetail);
}
