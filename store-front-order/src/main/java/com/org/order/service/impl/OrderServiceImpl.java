package com.org.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.clients.ProductClient;
import com.org.order.mapper.OrderMapper;
import com.org.order.service.OrderService;
import com.org.param.OrderParam;
import com.org.param.PageParam;
import com.org.param.ProductCollectParam;
import com.org.param.ProductNumberParam;
import com.org.pojo.Collect;
import com.org.pojo.Order;
import com.org.pojo.Product;
import com.org.pojo.User;
import com.org.utils.R;
import com.org.vo.AdminOrderVo;
import com.org.vo.CartVo;
import com.org.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 陈晨
 * @date 2023/5/29
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public R save(OrderParam orderParam) {
        //修改清空购物车的参数
        List<Integer> cartIdList = new ArrayList<>();
        //商品修改库存参数集合
        List<ProductNumberParam> productNumberParamList = new ArrayList<>();
        //修改批量插入数据库的参数
        List<Order> orderList = new ArrayList<>();
        Integer userId = orderParam.getUserId();
        long orderId = System.currentTimeMillis();

        for (CartVo cartVo : orderParam.getProducts()) {
            cartIdList.add(cartVo.getId());
            //修改信息存储
            ProductNumberParam productNumberParam = new ProductNumberParam();
            productNumberParam.setNum(cartVo.getNum());
            productNumberParam.setProductId(cartVo.getProductID());
            productNumberParamList.add(productNumberParam);
            //订单信息保存
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(orderId);
            order.setUserId(userId);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order);
        }
        //批量数据插入
        this.saveBatch(orderList);

        //修改商品库存
        rabbitTemplate.convertAndSend("topic.ex", "sub.number", productNumberParamList);
        //清空对应购物车数据即可
        rabbitTemplate.convertAndSend("topic.ex", "clear.cart", cartIdList);
        R ok = R.ok("订单生成成功!");
        log.info("OrderServiceImpl.save业务结束，结果:{}", ok);
        return ok;
    }

    @Override
    public R list(OrderParam orderParam) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id", orderParam.getUserId());
        List<Order> list = list(orderQueryWrapper);
        Map<Long, List<Order>> orderMap = list.stream().collect(Collectors.groupingBy(Order::getOrderId));

        List<Integer> productIdList = list.stream().map(Order::getProductId).collect(Collectors.toList());
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIdList);
        List<Product> productList = productClient.list(productCollectParam);
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));
        List<List<OrderVo>> result = new ArrayList<>();
        //遍历订单项集合
        for (List<Order> orderList : orderMap.values()) {
            //封装每一个订单
            List<OrderVo> orderVoList = new ArrayList<>();
            for (Order order : orderList) {
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order, orderVo);
                Product product = productMap.get(order.getProductId());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVoList.add(orderVo);
            }
            result.add(orderVoList);
        }
        R ok = R.ok("订单数据获取成功", result);
        return ok;
    }

    @Override
    public R check(Integer productId) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("product_id", productId);
        Long count = baseMapper.selectCount(orderQueryWrapper);
        if (count > 0) {
            return R.fail("订单中有" + count + "项引用改商品,删除失败");
        }
        return R.ok("无订单引用，删除成功");
    }

    @Override
    public R adminList(PageParam pageParam) {
        int offSet = (pageParam.getCurrentPage() - 1) * pageParam.getPageSize();
        int pageSize = pageParam.getPageSize();
        List<AdminOrderVo> adminOrderVoList = orderMapper.selectAdminOrder(offSet, pageSize);
        return R.ok("订单数据查询成功",adminOrderVoList);

    }
}