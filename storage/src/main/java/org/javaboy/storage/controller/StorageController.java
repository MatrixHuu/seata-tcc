package org.javaboy.storage.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.common.RespBean;
import org.javaboy.common.feign.StorageServiceApi;
import org.javaboy.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 14:26
 */
@RestController
public class StorageController implements StorageServiceApi {

    @Autowired
    StorageService storageService;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String productId, Integer count) {
        return storageService.prepare(productId, count);
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return storageService.commit(actionContext);
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return storageService.rollback(actionContext);
    }
}
