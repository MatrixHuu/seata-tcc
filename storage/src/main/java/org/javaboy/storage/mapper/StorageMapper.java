package org.javaboy.storage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/20 14:16
 */
@Mapper
public interface StorageMapper {

    @Update("update storage_tbl set count = count - #{count} where commodity_code = #{productId}")
    int deductStorage(@Param("productId") String productId, @Param("count") Integer count);

    @Select("select count from storage_tbl where commodity_code = #{commodity_code}")
    int getCountByCommodityCode(String commodity_code);

}
