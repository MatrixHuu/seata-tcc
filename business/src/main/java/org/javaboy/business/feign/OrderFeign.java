package org.javaboy.business.feign;

import org.javaboy.common.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 14:37
 */
@FeignClient("order")
public interface OrderFeign {
    @PostMapping("/createOrder")
    RespBean createOrder(@RequestParam("account") String account, @RequestParam("productId") String productId, @RequestParam("count") Integer count);
}
