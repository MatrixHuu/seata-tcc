package org.javaboy.order.controller;

import org.javaboy.common.RespBean;
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
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/createOrder")
    public RespBean createOrder(String account, String productId, Integer count) {
        if (orderService.createOrder(account, productId, count)) {
            return RespBean.ok("下单成功");
        }
        return RespBean.error("下单失败");
    }
}
