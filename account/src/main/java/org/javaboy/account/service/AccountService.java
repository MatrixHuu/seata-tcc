package org.javaboy.account.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.account.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 8:52
 */
@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountMapper accountMapper;

    public boolean prepare(String userId, Double money) {
        return false;
    }

    public boolean commit(BusinessActionContext actionContext) {
        return false;
    }

    public boolean rollback(BusinessActionContext actionContext) {
        return false;
    }
}
