package org.javaboy.business.service;

import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.javaboy.business.feign.StorageServiceApiImpl;
import org.javaboy.common.feign.OrderServiceApi;
import org.javaboy.common.feign.StorageServiceApi;
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
    StorageServiceApi storageServiceApi;
    @Autowired
    OrderServiceApi orderServiceApi;

    @GlobalTransactional
    public void purchase(String account, String productId, Integer count) {
        String xid = RootContext.getXID();
        BusinessActionContext actionContext = new BusinessActionContext();
        actionContext.setXid(xid);
        orderServiceApi.prepare(actionContext, account, productId, count);
        storageServiceApi.prepare(actionContext, productId, count);
    }
}
