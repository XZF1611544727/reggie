package com.juct.reggie.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import com.juct.reggie.common.util.ThreadLocalUtil;
import com.juct.reggie.domain.ShoppingCart;
import com.juct.reggie.mapper.ShoppingCartMapper;
import com.juct.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 13:30
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCart add(ShoppingCart cart) {
        //获取用户ID
        Long id = (Long) ThreadLocalUtil.get();
        cart.setUserId(id);
        //判断当前菜品||套餐是否存在
        ShoppingCart shoppingCart = shoppingCartMapper.getDishOrSetmeal(cart);
        if (shoppingCart != null) {
            shoppingCart.setNumber(shoppingCart.getNumber()+1);
            shoppingCartMapper.updateNumber(shoppingCart);
            return shoppingCart;
        }
        cart.setId(IdUtil.getSnowflakeNextId());
        cart.setNumber(1);
        shoppingCartMapper.add(cart);
        return cart;

    }

    @Override
    public List<ShoppingCart> list() {
        Long id = (Long) ThreadLocalUtil.get();
        return shoppingCartMapper.list(id);
    }

    @Override
    public ShoppingCart sub(ShoppingCart shoppingCart) {
        Integer number = shoppingCartMapper.getNumber(shoppingCart);
        if(number == 1){
            shoppingCartMapper.delete(shoppingCart);
            return shoppingCart;
        }else{
            shoppingCart.setNumber(--number);
            shoppingCartMapper.updateNumber(shoppingCart);
            return shoppingCart;
        }
    }

    @Override
    public void clean() {
        Long id = (Long) ThreadLocalUtil.get();
        shoppingCartMapper.clean(id);
    }
}
