package com.org.product.service;

import com.org.param.*;
import com.org.pojo.Product;
import com.org.utils.R;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/19
 */
public interface ProductService {
    /**
     * 单类别名称 查询热门商品 至多7条数据
     *
     * @param categoryName
     * @return
     */
    R promo(String categoryName);

    /**
     * 多类别热门商品查询，根据类别名称集合
     *
     * @param productHotParam
     * @return
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 查询类别商品集合
     *
     * @return
     */
    R categoryList();

    /**
     * 传入类别id，根据id查询并分页
     * 没有传入，查询全部
     *
     * @param productIdsParam
     * @return
     */
    R byCategory(ProductIdsParam productIdsParam);

    /**
     * 根据商品id查询商品详情
     *
     * @param productIdParam
     * @return
     */
    R detail(ProductIdParam productIdParam);

    /**
     * 根据商品id查询商品图片
     *
     * @param productIdParam
     * @return
     */
    R pictures(ProductIdParam productIdParam);

    /**
     * 搜索服务调用，获取全部商品数据
     * 进行同步
     *
     * @return
     */
    List<Product> allList();

    /**
     * 商品搜索
     *
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 根据商品id集合查询商品信息
     *
     * @param productCollectParam
     * @return
     */
    R ids(ProductCollectParam productCollectParam);

    /**
     * 根据商品id 查询商品id集合
     *
     * @param productIds
     * @return
     */
    List<Product> cartList(List<Integer> productIds);

    /**
     * 修改商品库存 增加销售量
     *
     * @param
     */
    void batchNumber(List<ProductNumberParam> productNumberParams);

    /**
     * 根据分类id查询商品
     *
     * @param categoryId
     * @return
     */
    Long adminCount(Integer categoryId);

    /**
     * 商品保存业务
     *
     * @param productParam
     * @return
     */
    R adminSave(ProductSaveParam productParam);

    /**
     * 商品更新业务
     *
     * @param product
     * @return
     */
    R adminUpdate(Product product);

    /**
     * 商品删除业务
     *
     * @param productId
     * @return
     */
    R adminRemove(Integer productId);
}
