package org.javaboy.account.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.account.service.AccountService;
import org.javaboy.common.RespBean;
import org.javaboy.common.feign.AccountServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 8:58
 */
@RestController
public class AccountController implements AccountServiceApi {
    @Autowired
    AccountService accountService;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String userId, Double money) {
        return accountService.prepare(userId, money);
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return accountService.commit(actionContext);
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return accountService.rollback(actionContext);
    }
}
