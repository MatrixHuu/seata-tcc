package org.javaboy.order.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.common.RespBean;
import org.javaboy.common.feign.OrderServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.javaboy.order.service.OrderService;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 12:06
 */
@RestController
public class OrderController implements OrderServiceApi {
    @Autowired
    OrderService orderService;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String userId, String productId, Integer count) {
        return orderService.prepare(actionContext, userId, count, productId);
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return orderService.commit(actionContext);
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return orderService.rollback(actionContext);
    }
}
