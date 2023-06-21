package org.javaboy.storage.controller;

import org.javaboy.common.RespBean;
import org.javaboy.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 14:26
 */
@RestController
public class StorageController {

    @Autowired
    StorageService storageService;

    @PostMapping("/deduct")
    public RespBean deduct(String productId, Integer count) {
        if (storageService.deduct(productId, count)) {
            return RespBean.ok("扣库存成功");
        }
        return RespBean.error("扣库存失败");
    }

}
