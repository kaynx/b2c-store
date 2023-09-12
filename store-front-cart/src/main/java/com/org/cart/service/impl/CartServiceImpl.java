package com.org.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.cart.mapper.CartMapper;
import com.org.cart.service.CartService;
import com.org.clients.ProductClient;
import com.org.param.CartParam;
import com.org.param.ProductCollectParam;
import com.org.param.ProductIdParam;
import com.org.pojo.Cart;
import com.org.pojo.Product;
import com.org.utils.R;
import com.org.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 陈晨
 * @date 2023/5/28
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductClient productClient;

    @Override
    public R save(CartParam cartParam) {
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartParam.getProductId());
        Product product = productClient.cartDetail(productIdParam);
        if (product == null) {
            log.info("CartServiceImpl.save业务开始，商品被移除,无法添加!");
            return R.fail("商品已经被删除,无法添加!");
        }
        //检查库存
        if (product.getProductNum() == 0) {
            R fail = R.fail("已经没有库存,无法购买!");
            //没有库存的错误码
            fail.setCode("003");
            return fail;
        }
        //检查是不是第一次添加
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id", cartParam.getUserId());
        cartQueryWrapper.eq("product_id", cartParam.getProductId());
        Cart cart = cartMapper.selectOne(cartQueryWrapper);
        if (cart != null) {
            cart.setNum(cart.getNum() + 1);
            cartMapper.updateById(cart);
            //更新属性 + 1
            R ok = R.ok("商品已经在购物车,数量+1!");
            ok.setCode("002");
            return ok;
        }
        //添加购物车
        cart = new Cart();
        cart.setNum(1);
        cart.setUserId(cartParam.getUserId());
        cart.setProductId(cartParam.getProductId());
        cartMapper.insert(cart);
        CartVo cartVo = new CartVo(product, cart);
        log.info("CartServiceImpl.save业务结束，结果:{}", cartVo);
        return R.ok(cartVo);
    }

    /**
     * 查询购物车数据集合
     *
     * @param cartParam
     * @return
     */
    @Override
    public R list(CartParam cartParam) {
        //获取用户id
        Integer userId = cartParam.getUserId();
        //查询用户id对应的购物车数据
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        if (cartList == null || cartList.size() == 0) {
            return R.ok("购物车没有数据!", cartList);
        }
        //封装商品集合,查询商品数据
        List<Integer> productIdList = new ArrayList<>();
        for (Cart cart : cartList) {
            productIdList.add(cart.getProductId());
        }

        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIdList);
        List<Product> productList = productClient.list(productCollectParam);
        //集合转map!
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));
        System.out.println("map = " + productMap);
        //结果封装即可
        List<CartVo> list = new ArrayList<>(cartList.size());
        for (Cart cart : cartList) {
            CartVo cartVo = new CartVo(productMap.get(cart.getProductId()), cart);
            list.add(cartVo);
        }

        R ok = R.ok(list);
        log.info("CartServiceImpl.list业务结束，结果:{}", ok);
        return ok;
    }

    @Override
    public R update(CartParam cartParam) {
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartParam.getProductId());
        Product product = productClient.cartDetail(productIdParam);
        if (cartParam.getNum() > product.getProductNum()) {
            return R.fail("修改失败，库存不足");
        }
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id", cartParam.getUserId());
        cartQueryWrapper.eq("product_id", cartParam.getProductId());
        Cart cart = cartMapper.selectOne(cartQueryWrapper);
        cart.setNum(cartParam.getNum());
        int rows = cartMapper.updateById(cart);
        log.info("CartServiceImpl.update，结果:{}", rows);
        return R.ok("修改购物车数量成功");

    }

    @Override
    public R remove(CartParam cartParam) {
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id", cartParam.getUserId());
        cartQueryWrapper.eq("product_id", cartParam.getProductId());
        int rows = cartMapper.delete(cartQueryWrapper);
        log.info("CartServiceImpl.remove，结果:{}", rows);
        return R.ok("删除购物车成功");

    }

    @Override
    public void removeBatchByIds(List<Integer> cartIds) {
        cartMapper.deleteBatchIds(cartIds);
        log.info("CartServiceImpl.removeBatchByIds，结果:{}", cartIds);
    }

    @Override
    public R check(Integer productId) {
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("product_id", productId);
        Long count = cartMapper.selectCount(cartQueryWrapper);
        if (count > 0) {
            return R.fail("有：" + count + "件商品被引用!删除失败");
        }
        return R.ok("购物车无商品引用");
    }

}
