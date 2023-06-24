package org.javaboy.business.feign;

import org.javaboy.common.feign.StorageServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/24 17:41
 */
@FeignClient("storage")
public interface StorageServiceApiImpl extends StorageServiceApi {
}
