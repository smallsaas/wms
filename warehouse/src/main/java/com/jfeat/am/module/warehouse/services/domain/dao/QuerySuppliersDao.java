package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.SuppliersModel;
import com.jfeat.am.module.warehouse.services.domain.model.SuppliersRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QuerySuppliersDao extends BaseMapper<SuppliersRecord> {
    List<SuppliersRecord> findSuppliersPage(Page<SuppliersRecord> page, @Param("record") SuppliersRecord record, @Param("orderBy") String orderBy);

    SuppliersModel supplierProducts(@Param("id")Long id);
}