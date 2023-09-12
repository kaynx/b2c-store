package com.org.product.listener;

import com.org.param.OrderProductParam;
import com.org.param.ProductNumberParam;
import com.org.product.service.ProductService;
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
public class ProductRabbitMqListener {
    @Autowired
    private ProductService productService;

    /**
     * 修改库存数据
     *
     * @param
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sub.queue"),
            exchange = @Exchange("topic.ex"),
            key = "sub.number"
    ))
    public void subNumber(List<ProductNumberParam> productNumberParams) {
        //调用业务修改库存即可
        productService.batchNumber(productNumberParams);
    }
}
