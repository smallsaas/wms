package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.SkuStorageDetails;
import com.jfeat.am.module.warehouse.services.domain.model.InventoryRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-23
 */
public interface QueryInventoryDao extends BaseMapper<InventoryRecord> {
    List<InventoryRecord> findInventoryPage(Page<InventoryRecord> page,
                                            @Param("warehouseName") String warehouseName,
                                            @Param("skuName") String skuName,
                                            @Param("record") InventoryRecord record,
                                            @Param("orderBy") String orderBy);


    List<SkuStorageDetails> skuStorageDetails(Page<SkuStorageDetails> page,
                                              @Param("skuId") Long skuId,
                                              @Param("warehouseName") String warehouseName);
}