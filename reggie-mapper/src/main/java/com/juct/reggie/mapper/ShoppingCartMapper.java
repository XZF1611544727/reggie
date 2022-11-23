package com.juct.reggie.mapper;

import com.juct.reggie.domain.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 13:32
 */
@Mapper
public interface ShoppingCartMapper {

    ShoppingCart getDishOrSetmeal(ShoppingCart cart);

//    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumber(ShoppingCart shoppingCart);

    void add(ShoppingCart cart);

    @Select("select * from shopping_cart where user_id = #{id}")
    List<ShoppingCart> list(Long id);

    Integer getNumber(ShoppingCart shoppingCart);

    void delete(ShoppingCart shoppingCart);

    @Delete("delete from shopping_cart where user_id = #{id}")
    void clean(Long id);
}
