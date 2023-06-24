package org.javaboy.order.feign;

import org.javaboy.common.feign.AccountServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/24 16:41
 */
@FeignClient("account")
public interface AccountServiceApiImpl extends AccountServiceApi {
}
