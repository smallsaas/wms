package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.WarehouseSlotRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryWarehouseSlotDao extends BaseMapper<WarehouseSlotRecord> {
    List<WarehouseSlotRecord> findWarehouseSlotPage(Page<WarehouseSlotRecord> page, @Param("record") WarehouseSlotRecord record, @Param("orderBy") String orderBy);
}