package org.javaboy.account.model;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/22 14:01
 */
public class Account {
    private Integer id;
    private String userId;
    private Double money;
    private Double freezeMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(Double freezeMoney) {
        this.freezeMoney = freezeMoney;
    }
}
