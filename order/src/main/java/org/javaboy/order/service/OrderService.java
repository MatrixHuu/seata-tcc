package org.javaboy.order.service;

import org.javaboy.common.RespBean;
import org.javaboy.order.feign.AccountFeign;
import org.javaboy.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 11:40
 */

@Service
public class OrderService {
    @Autowired
    AccountFeign accountFeign;
    @Autowired
    OrderMapper orderMapper;

    public boolean createOrder(String account, String productId, Integer count) {
        RespBean respBean = accountFeign.deduct(account, count * 100.0);
        int i = orderMapper.addOrder(account, productId, count, count * 100.0);
        return i == 1 && respBean.getStatus() == 200;
    }
}
