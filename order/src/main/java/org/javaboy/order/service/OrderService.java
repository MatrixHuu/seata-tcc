package org.javaboy.order.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.common.feign.AccountServiceApi;
import org.javaboy.order.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 11:40
 */

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    AccountServiceApi accountServiceApi;
    @Autowired
    OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean prepare(BusinessActionContext actionContext, String userId, Integer count, String productId) {
        boolean prepare = accountServiceApi.prepare(actionContext, userId, count * 100.0);
        logger.info("{} 用户购买了 {} 商品，共计 {} 件， 预下单成功", userId, productId, count);
        return prepare;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean commit(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        Integer i = orderMapper.addOrder(userId, productId, count, count * 100.0);
        logger.info("{} 用户购买了 {} 商品，共计 {} 件，下单成功", userId, productId, count);
        return i == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean rollback(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        logger.info("{} 用户购买了 {} 商品，共计 {} 件，订单回滚成功", userId, productId, count);
        return true;
    }
}
