package com.org.cart.listener;

import com.org.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/29
 */
@Component
public class CartRabbitMqListener {
    @Autowired
    private CartService cartService;

    /**
     * 购物车数据清空监听
     * @param cartIds //要清空的购物车数据集合
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "clear.queue"),
            exchange = @Exchange("topic.ex"),
            key = "clear.cart"
    ))
    public void subNumber(List<Integer> cartIds){
        System.out.println("CartListener.subNumber");
        System.out.println("cartIds = " + cartIds);

        //调用业务修改库存即可
        cartService.removeBatchByIds(cartIds);
    }

}
