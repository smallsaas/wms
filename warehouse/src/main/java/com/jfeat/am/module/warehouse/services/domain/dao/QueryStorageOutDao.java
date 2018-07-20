package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.StorageOutRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-20
 */
public interface QueryStorageOutDao extends BaseMapper<StorageOutRecord> {
    List<StorageOutRecord> findStorageOutPage(Page<StorageOutRecord> page, @Param("record") StorageOutRecord record, @Param("orderBy") String orderBy);

    StorageOutRecord storagesOutDetails(@Param("id")Long id);
}