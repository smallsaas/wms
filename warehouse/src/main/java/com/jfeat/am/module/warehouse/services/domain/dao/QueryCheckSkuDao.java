package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.CheckSkuRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-23
 */
public interface QueryCheckSkuDao extends BaseMapper<CheckSkuRecord> {
    List<CheckSkuRecord> findCheckSkuPage(Page<CheckSkuRecord> page, @Param("record") CheckSkuRecord record, @Param("orderBy") String orderBy);
}