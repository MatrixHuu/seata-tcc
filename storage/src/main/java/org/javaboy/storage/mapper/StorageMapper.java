package org.javaboy.storage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.storage.model.Storage;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 14:16
 */
@Mapper
public interface StorageMapper {

    @Select("select * from storage_tbl where productId = #{productId}")
    Storage getStorageByProductId(String productId);

    @Update("update storage_tbl set count = #{count}, freezeCount = #{freezeCount} where productId = #{productId}")
    Integer updateStorage(Storage storage);
}
