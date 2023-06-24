package org.javaboy.account.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.account.mapper.AccountMapper;
import org.javaboy.account.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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

    @Transactional(rollbackFor = Exception.class)
    public boolean prepare(String userId, Double money) {
        Account account = accountMapper.getAccountByUserId(userId);
        if (account == null) {
            throw new RuntimeException("账户不存在");
        }
        if (account.getMoney() < money) {
            throw new RuntimeException("账户余额不足，预扣款失败");
        }
        account.setFreezeMoney(account.getFreezeMoney() + money);
        account.setMoney(account.getMoney() - money);
        Integer i = accountMapper.updateAccount(account);
        logger.info("{} 账户预扣款 {} 元", userId, money);
        return i == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean commit(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        double money = ((BigDecimal) actionContext.getActionContext("money")).doubleValue();
        Account account = accountMapper.getAccountByUserId(userId);
        if (account.getFreezeMoney() < money) {
            throw new RuntimeException("账户余额不足，扣款失败");
        }
        account.setFreezeMoney(account.getFreezeMoney() - money);
        Integer i = accountMapper.updateAccount(account);
        logger.info("{} 账户扣款 {} 元", userId, money);
        return i == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean rollback(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        double money = ((BigDecimal) actionContext.getActionContext("money")).doubleValue();
        Account account = accountMapper.getAccountByUserId(userId);
        if (account.getFreezeMoney() >= money) {
            account.setMoney(account.getMoney() + money);
            account.setFreezeMoney(account.getFreezeMoney() - money);
            Integer i = accountMapper.updateAccount(account);
            logger.info("{} 账户释放冻结金额 {} 元", userId, money);
            return i == 1;
        }
        logger.info("{} 账户冻结金额已释放", userId);
        return true;
        }
    }
