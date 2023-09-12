package com.org.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.clients.*;
import com.org.param.*;
import com.org.pojo.Picture;
import com.org.pojo.Product;
import com.org.product.mapper.PictureMapper;
import com.org.product.mapper.ProductMapper;
import com.org.product.service.ProductService;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 陈晨
 * @date 2023/5/19
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private SearchClient searchClient;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private CartClient cartClient;
    @Autowired
    private CollectClient collectClient;


    @Cacheable(value = "list.product", key = "#categoryName")
    @Override
    public R promo(String categoryName) {
        R r = categoryClient.getCategoryByCategoryName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo()业务结束,结果:{}", "类别查询失败");
            return r;
        }
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();
        Integer categoryId = (Integer) map.get("category_id");
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("category_id", categoryId);
        productQueryWrapper.orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1, 7);

        page = productMapper.selectPage(page, productQueryWrapper);
        //指定页的数据
        List<Product> productList = page.getRecords();
        log.info("ProductServiceImpl.promo()业务结束,结果:{}", productList);
        return R.ok("数据查询成功", productList);
    }

    @Cacheable(value = "list.product", key = "#productHotParam.categoryName")
    @Override
    public R hots(ProductHotParam productHotParam) {
        R r = categoryClient.hotsCategory(productHotParam);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.hots()业务结束,结果:{}", r.getMsg());
            return r;
        }
        List<Object> ids = (List<Object>) r.getData();
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.in("category_id", ids);
        productQueryWrapper.orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, productQueryWrapper);
        List<Product> records = page.getRecords();
        R ok = R.ok("多类别热门商品查询成功", records);
        log.info("ProductServiceImpl.hots()业务结束,结果:{}", ok);
        return ok;

    }

    @Override
    public R categoryList() {
        R ok = categoryClient.list();
        log.info("ProductServiceImpl.categoryList()业务结束,结果:{}", ok);
        return ok;

    }

    @Cacheable(value = "list.product", key = "#productIdsParam.categoryID+'-'+#productIdsParam.currentPage+'-'+#productIdsParam.pageSize")
    @Override
    public R byCategory(ProductIdsParam productIdsParam) {
        List<Integer> categoryId = productIdsParam.getCategoryID();
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();

        if (!categoryId.isEmpty()) {
            productQueryWrapper.in("category_id", categoryId);
        }
        IPage<Product> page = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());
        page = productMapper.selectPage(page, productQueryWrapper);
        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());
        log.info("ProductServiceImpl.byCategory()业务结束,结果:{}", ok);
        return ok;
    }

    @Cacheable(value = "product", key = "#productIdParam.productID")
    @Override
    public R detail(ProductIdParam productIdParam) {
        Product product = productMapper.selectById(productIdParam.getProductID());
        R ok = R.ok(product);
        log.info("ProductServiceImpl.detail()业务结束,结果:{}", ok);
        return ok;
    }

    @Cacheable(value = "picture", key = "#productIdParam.productID")
    @Override
    public R pictures(ProductIdParam productIdParam) {
        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
        pictureQueryWrapper.eq("product_id", productIdParam.getProductID());
        List<Picture> pictureList = pictureMapper.selectList(pictureQueryWrapper);
        R ok = R.ok(pictureList);
        log.info("ProductServiceImpl.pictures()业务结束,结果:{}", ok);
        return ok;
    }

    @Cacheable(value = "list.category", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public List<Product> allList() {
        List<Product> productList = productMapper.selectList(null);
        log.info("ProductServiceImpl.allList()业务结束,结果:{}", productList.size());
        return productList;
    }

    @Override
    public R search(ProductSearchParam productSearchParam) {
        R ok = searchClient.searchProduct(productSearchParam);
        log.info("ProductServiceImpl.search()业务结束,结果:{}", ok);
        return ok;
    }

    @Cacheable(value = "list.product", key = "#productCollectParam.productIds")
    @Override
    public R ids(ProductCollectParam productCollectParam) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.in("product_id", productCollectParam.getProductIds());
        List<Product> productList = productMapper.selectList(productQueryWrapper);
        R ok = R.ok("类别信息查询成功", productList);
        log.info("ProductServiceImpl.ids()业务结束,结果:{}", ok);
        return ok;
    }

    @Override
    public List<Product> cartList(List<Integer> productIds) {

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.in("product_id", productIds);
        List<Product> productList = productMapper.selectList(productQueryWrapper);
        log.info("ProductServiceImpl.cartList()业务结束,结果:{}", productList);
        return productList;

    }


    @Override
    @Transactional
    public void batchNumber(List<ProductNumberParam> productNumberParams) {

        //将productNumberParams转成map
        //使用id作为key, item做值, 比较相邻的两次key,如果相同,去掉重读!
        Map<Integer, ProductNumberParam> productNumberParamMap = productNumberParams.stream().collect(Collectors.toMap(ProductNumberParam::getProductId, v -> v));

        //封装商品集合
        Set<Integer> productIds = productNumberParamMap.keySet();

        //查询
        List<Product> productList = baseMapper.selectBatchIds(productIds);
        //修改

        for (Product product : productList) {
            //设置新库存
            product.setProductNum(product.getProductNum() - productNumberParamMap.get(product.getProductId()).getNum());
            //设置销售量
            product.setProductSales(product.getProductSales() + productNumberParamMap.get(product.getProductId()).getNum());
        }

        //批量数据更新
        this.updateBatchById(productList);
    }

    @Override
    public Long adminCount(Integer categoryId) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("category_id", categoryId);
        Long aLong = productMapper.selectCount(productQueryWrapper);
        log.info("ProductServiceImpl.adminCount()业务结束,结果:{}", aLong);
        return aLong;

    }

    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public R adminSave(ProductSaveParam productParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productParam, product);
        int rows = productMapper.insert(product);
        log.info("ProductServiceImpl.adminSave()业务结束,结果:{}", rows);
        //商品图片获取
        String pictures = productParam.getPictures();
        if (!StringUtils.isEmpty(pictures)) {
            //截取特殊字符串
            String[] urls = pictures.split("\\+");
            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                pictureMapper.insert(picture);
            }
        }
        //同步搜索服务数据
        searchClient.saveOrUpdate(product);
        return R.ok("商品数据添加成功");
    }

    @Override
    public R adminUpdate(Product product) {
        productMapper.updateById(product);
        searchClient.saveOrUpdate(product);
        return R.ok("商品数据更新成功");
    }

    @Caching(evict = {
            @CacheEvict(value = "list.product" ,allEntries = true),
            @CacheEvict(value = "product" ,key = "#productId")
    })
    @Override
    public R adminRemove(Integer productId) {
        R r = cartClient.removeCheck(productId);
        if ("004".equals(r.getCode())) {
            log.info("ProductServiceImpl.adminRemove()业务结束,结果:{}", r.getMsg());
            return r;
        }
        r = orderClient.check(productId);
        if ("004".equals(r.getCode())) {
            log.info("ProductServiceImpl.adminRemove()业务结束,结果:{}", r.getMsg());
            return r;
        }
        //删除商品
        productMapper.deleteById(productId);
        //删除商品图片
        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
        pictureQueryWrapper.eq("product_id", productId);
        pictureMapper.delete(pictureQueryWrapper);
        searchClient.removeProduct(productId);
        collectClient.removeByPid(productId);
        return R.ok("商品删除成功");
    }
}
