package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.SkuStorageDetails;
import com.jfeat.am.module.warehouse.services.domain.model.InventoryRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 获取当前某仓库某sku的库存量
     * */
    @Select("select wms_inventory.valid_sku from wms_inventory where wms_inventory.sku_id = #{skuId} and wms_inventory.warehouse_id = #{warehouseId}")
    Integer nowInventoryCount(@Param("skuId")Long skuId,
                              @Param("warehouseId")Long warehouseId);

}