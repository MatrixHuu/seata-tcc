package org.javaboy.order.feign;

import org.javaboy.common.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 11:35
 */
@FeignClient("account")
public interface AccountFeign {
    @PostMapping("/deductAccount")
    RespBean deduct(@RequestParam("account") String account, @RequestParam("money") Double money);
}
