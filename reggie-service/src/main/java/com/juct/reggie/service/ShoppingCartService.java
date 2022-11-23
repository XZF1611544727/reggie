package com.juct.reggie.service;

import com.juct.reggie.domain.ShoppingCart;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 13:30
 */
public interface ShoppingCartService {

    /**
     * 添加购物车
     * @param cart
     */
    ShoppingCart add(ShoppingCart cart);

    /**
     * 查询购物车
     * @return
     */
    List<ShoppingCart> list();

    ShoppingCart sub(ShoppingCart shoppingCart);

    void clean();
}
