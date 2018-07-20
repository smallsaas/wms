package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.WarehouseRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryWarehouseDao extends BaseMapper<WarehouseRecord> {
    List<WarehouseRecord> findWarehousePage(Page<WarehouseRecord> page, @Param("record") WarehouseRecord record, @Param("orderBy") String orderBy);
}