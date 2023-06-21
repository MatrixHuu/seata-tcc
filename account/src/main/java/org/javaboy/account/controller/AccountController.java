package org.javaboy.account.controller;

import org.javaboy.account.service.AccountService;
import org.javaboy.common.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 8:58
 */
@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/deductAccount")
    public RespBean deduct(String account, Double money) {
        if (accountService.deductAccount(account, money)) {
            return RespBean.ok("扣款成功");
        }
        return RespBean.error("扣款失败");
    }
}
