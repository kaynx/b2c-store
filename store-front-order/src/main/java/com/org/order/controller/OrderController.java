package com.org.order.controller;

import com.org.clients.ProductClient;
import com.org.order.service.OrderService;
import com.org.param.OrderParam;
import com.org.param.PageParam;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈晨
 * @date 2023/5/29
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("save")
    public R save(@RequestBody OrderParam orderParam) {
        return orderService.save(orderParam);
    }

    @PostMapping("list")
    public R list(@RequestBody OrderParam orderParam, BindingResult result) {
        if (result.hasErrors()) {
            R.fail("参数异常，查询错误");
        }
        return orderService.list(orderParam);
    }
    @PostMapping("remove/check")
    public R check(@RequestBody Integer productId) {
        return orderService.check(productId);
    }
    @PostMapping("admin/list")
    public R adminList(@RequestBody PageParam pageParam){
        return orderService.adminList(pageParam);
    }
}
