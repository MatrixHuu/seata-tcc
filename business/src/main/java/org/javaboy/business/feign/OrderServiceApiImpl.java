package org.javaboy.business.feign;

import org.javaboy.common.feign.OrderServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/24 17:40
 */
@FeignClient("order")
public interface OrderServiceApiImpl extends OrderServiceApi {
}
