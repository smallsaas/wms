package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.CheckRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.domain.model.CheckSkuRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-23
 */
public interface QueryCheckDao extends BaseMapper<CheckRecord> {
    List<CheckRecord> findCheckPage(Page<CheckRecord> page, @Param("record") CheckRecord record, @Param("orderBy") String orderBy);


    List<CheckSkuRecord> skuRecords(@Param("checkId")Long checkId);

    // 盘点 初始化的时候某个仓库某个商品的库存数量
    @Select("select wms_inventory.valid_sku from wms_inventory where wms_inventory.warehouse_id = #{warehouseId} and wms_inventory.sku_id = #{skuId}")
    Integer validCount(@Param("warehouseId")Long warehouseId,
                       @Param("skuId")Long skuId);

    // 某次盘点总的缺失值
    @Select("select sum(wms_check_sku.profit_lost) from wms_check_sku left join wms_check on wms_check.id = wms_check_sku.check_id where wms_check.id = #{checkId}")
    Integer profitLost(@Param("checkId")Long checkId);
}