package com.org.clients;

import com.org.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 陈晨
 * @date 2023/6/16
 */
@FeignClient("collect-service")
public interface CollectClient {
    @PostMapping("collect/remove/product")
    R removeByPid(@RequestBody Integer productId);
}
