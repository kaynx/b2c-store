package com.org.admin.service.impl;

import com.org.admin.service.OrderService;
import com.org.clients.OrderClient;
import com.org.param.PageParam;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 陈晨
 * @date 2023/6/16
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderClient orderClient;

    @Override
    public R list(PageParam pageParam) {
        R ok = orderClient.adminList(pageParam);
        log.info("OrderServiceImpl.list()业务结束，结果{}", ok);
        return ok;
    }
}
