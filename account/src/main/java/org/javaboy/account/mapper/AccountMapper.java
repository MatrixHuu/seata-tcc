package org.javaboy.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.account.model.Account;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 8:41
 */
@Mapper
public interface AccountMapper {

    @Select("select * from account_tbl where userId = #{userId}")
    Account getAccountByUserId(String userId);

    @Update("update account_tbl set money = #{money}, freezeMoney = #{freezeMoney} where userId = #{userId}")
    Integer updateAccount(Account account);
}
