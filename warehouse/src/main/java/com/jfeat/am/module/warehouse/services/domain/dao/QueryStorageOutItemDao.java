package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.StorageOutItemRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-20
 */
public interface QueryStorageOutItemDao extends BaseMapper<StorageOutItemRecord> {
    List<StorageOutItemRecord> findStorageOutItemPage(Page<StorageOutItemRecord> page, @Param("record") StorageOutItemRecord record, @Param("orderBy") String orderBy);
}