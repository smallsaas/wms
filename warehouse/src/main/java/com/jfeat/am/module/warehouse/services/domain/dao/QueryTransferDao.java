package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.StorageOutItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.TransferRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryTransferDao extends BaseMapper<TransferRecord> {
    List<TransferRecord> findTransferPage(Page<TransferRecord> page,
                                          @Param("warehouseId")Long warehouseId,
                                          @Param("record") TransferRecord record,
                                          @Param("orderBy") String orderBy,
                                          @Param("startTime") Date startTime,
                                          @Param("endTime") Date endTime);


    // 调拨 详情
    List<StorageOutItemRecord> outItemRecords(@Param("outId")Long outId);

    // Draft 调拨 详情
    List<StorageOutItemRecord> draftOutItemRecords(@Param("outItemId")Long outItemId);

    // 为了前端映射 key
    List<StorageOutItem> outItems(@Param("outItemId")Long outItemId);
}