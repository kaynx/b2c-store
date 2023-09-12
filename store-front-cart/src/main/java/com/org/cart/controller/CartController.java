package com.org.cart.controller;

import com.org.cart.service.CartService;
import com.org.param.CartParam;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈晨
 * @date 2023/5/28
 */
@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("save")
    public R save(@RequestBody @Validated CartParam cartParam, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("购物车添加失败");
        }
        return cartService.save(cartParam);
    }

    @PostMapping("list")
    public R list(@RequestBody CartParam cartParam) {
        return cartService.list(cartParam);
    }

    @PostMapping("update")
    public R update(@RequestBody CartParam cartParam) {
        return cartService.update(cartParam);
    }

    @PostMapping("remove")
    public R remove(@RequestBody CartParam cartParam) {
        return cartService.remove(cartParam);
    }

    @PostMapping("remove/check")
    public R removeCheck(@RequestBody Integer productId){
        return cartService.check(productId);
    }
}
