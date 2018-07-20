package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.StorageOutItemRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-21
 */
public interface QueryStorageInItemDao extends BaseMapper<StorageOutItemRecord> {
    List<StorageOutItemRecord> findStorageInItemPage(Page<StorageOutItemRecord> page, @Param("record") StorageOutItemRecord record, @Param("orderBy") String orderBy);
}