package org.javaboy.storage.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.storage.mapper.StorageMapper;
import org.javaboy.storage.model.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 14:21
 */
@Service
public class StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);
    @Autowired
    StorageMapper storageMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean prepare(String productId, Integer count) {
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage == null) {
            throw new RuntimeException("商品不存在");
        }
        if (storage.getCount() < count) {
            throw new RuntimeException("库存不足，预扣库存失败");
        }
        storage.setFreezeCount(storage.getFreezeCount() + count);
        storage.setCount(storage.getCount() - count);
        Integer i = storageMapper.updateStorage(storage);
        logger.info("{} 商品库存冻结 {} 个", productId, count);
        return i == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean commit(BusinessActionContext actionContext) {
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage.getFreezeCount() < count) {
            throw new RuntimeException("库存不足，扣库存失败");
        }
        storage.setFreezeCount(storage.getFreezeCount() - count);
        Integer i = storageMapper.updateStorage(storage);
        logger.info("{} 商品扣库存 {} 个", productId, count);
        return i == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean rollback(BusinessActionContext actionContext) {
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage.getFreezeCount() >= count) {
            storage.setCount(storage.getCount() + count);
            storage.setFreezeCount(storage.getFreezeCount() - count);
            Integer i = storageMapper.updateStorage(storage);
            logger.info("{} 商品释放冻结库存 {} 个", productId, count);
            return i == 1;
        }
        logger.info("{} 商品冻结的库存已释放", productId);
        return true;
    }
}
