package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.ProcurementRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryProcurementDao extends BaseMapper<ProcurementRecord> {
    List<ProcurementRecord> findProcurementPage(Page<ProcurementRecord> page, @Param("record") ProcurementRecord record, @Param("orderBy") String orderBy);
}