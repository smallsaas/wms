package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.WarehouseRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryWarehouseDao extends BaseMapper<WarehouseRecord> {
    List<WarehouseRecord> findWarehousePage(Page<WarehouseRecord> page, @Param("warehouseId")Long warehouseId,@Param("record") WarehouseRecord record, @Param("orderBy") String orderBy);


    @Select("select wms_warehouse.warehouse_name as warehouseName from wms_warehouse where wms_warehouse.id = #{warehouseId}")
    String warehouseName(@Param("warehouseId")Long warehouseId);
}