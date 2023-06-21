package org.javaboy.business.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.javaboy.business.feign.OrderFeign;
import org.javaboy.business.feign.StorageFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 14:40
 */
@Service
public class BusinessService {
    @Autowired
    StorageFeign storageFeign;
    @Autowired
    OrderFeign orderFeign;

    @GlobalTransactional
    public void purchase(String account, String productId, Integer count) {
        storageFeign.deduct(productId, count);
        orderFeign.createOrder(account, productId, count);
    }
}
