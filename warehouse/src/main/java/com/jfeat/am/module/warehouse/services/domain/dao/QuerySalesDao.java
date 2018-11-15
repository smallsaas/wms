package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.SalesRecord;
import com.jfeat.am.module.warehouse.services.domain.model.SuppliersModel;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-11-14
 */
public interface QuerySalesDao extends BaseMapper<SalesRecord> {
    List<SalesRecord> findSalesPage(Page<SalesRecord> page, @Param("record") SalesRecord record, @Param("orderBy") String orderBy);
}