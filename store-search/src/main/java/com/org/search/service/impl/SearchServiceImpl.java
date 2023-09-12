package com.org.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.doc.ProductDoc;
import com.org.param.ProductSearchParam;
import com.org.pojo.Product;
import com.org.search.service.SearchService;
import com.org.utils.R;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 陈晨
 * @date 2023/5/22
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public R search(ProductSearchParam productSearchParam) throws JsonProcessingException {
        SearchRequest searchRequest = new SearchRequest("product");

        String search = productSearchParam.getSearch();
        if (StringUtils.isEmpty(search)) {
            //如果为null，查询全部
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        } else {
            //不为空 all字段进行搜索
            searchRequest.source().query(QueryBuilders.matchQuery("all", search));
        }

        //设置分页参数
        searchRequest.source().from((productSearchParam.getCurrentPage() - 1) * productSearchParam.getPageSize());
        searchRequest.source().size(productSearchParam.getPageSize());

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //结果集解析
        //获取集中的结果
        SearchHits hits = searchResponse.getHits();
        //获取符合的数量
        long value = hits.getTotalHits().value;

        SearchHit[] searchHits = hits.getHits();

        ArrayList<Product> productList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (SearchHit searchHit : searchHits) {
            //获取json数据
            String sourceAsString = searchHit.getSourceAsString();
            Product product = objectMapper.readValue(sourceAsString, Product.class);
            productList.add(product);
        }
        R ok = R.ok(null, productList, value);
        return ok;
    }

    @Override
    public R saveProduct(Product product) throws IOException {
        IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
        ProductDoc productDoc = new ProductDoc(product);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productDoc);
        indexRequest.source(json, XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return R.ok("数据同步成功");
    }

    @Override
    public R removeProduct(Integer productId) throws IOException {
        DeleteRequest request = new DeleteRequest("product").id(productId.toString());
        restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        return R.ok("数据库数据删除成功");
    }
}
