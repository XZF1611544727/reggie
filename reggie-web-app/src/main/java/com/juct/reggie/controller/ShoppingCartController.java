package com.juct.reggie.controller;

import com.juct.reggie.common.R;
import com.juct.reggie.domain.ShoppingCart;
import com.juct.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 谢智峰
 * @create 2022-11-23 13:15
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    @PostMapping("/add")
    public R add(
            @RequestBody ShoppingCart cart
    ) {
        ShoppingCart shoppingCart = shoppingCartService.add(cart);
        return R.success(shoppingCart);
    }

    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        List<ShoppingCart> list = shoppingCartService.list();
        return R.success(list);
    }


    @PostMapping("/sub")
    public R sub(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart cart = shoppingCartService.sub(shoppingCart);
        return R.success(cart);
    }

    @DeleteMapping("/clean")
    public R clean() {
        shoppingCartService.clean();
        return R.success(null);
    }
}
