package org.javaboy.storage.model;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/24 17:12
 */
public class Storage {
    private Integer id;
    private String productId;
    private Integer count;
    private Integer freezeCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFreezeCount() {
        return freezeCount;
    }

    public void setFreezeCount(Integer freezeCount) {
        this.freezeCount = freezeCount;
    }
}
