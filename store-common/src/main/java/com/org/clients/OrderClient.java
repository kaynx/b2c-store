package com.org.clients;

import com.org.param.PageParam;
import com.org.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 陈晨
 * @date 2023/6/16
 */
@FeignClient("order-service")
public interface OrderClient {
    @PostMapping("order/remove/check")
    R check(@RequestBody Integer productId);

    @PostMapping("order/admin/list")
    R adminList(@RequestBody PageParam pageParam);
}
