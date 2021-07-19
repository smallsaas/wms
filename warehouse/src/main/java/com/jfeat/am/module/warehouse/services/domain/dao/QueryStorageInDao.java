package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.StorageInRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-21
 */
public interface QueryStorageInDao extends BaseMapper<StorageInRecord> {
    List<StorageInRecord> findStorageInPage(Page<StorageInRecord> page, @Param("record") StorageInRecord record, @Param("orderBy") String orderBy);
    StorageInRecord storagesInDetails(@Param("id")Long id);
}