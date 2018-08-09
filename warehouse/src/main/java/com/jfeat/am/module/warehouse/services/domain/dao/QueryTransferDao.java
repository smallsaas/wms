package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.StorageOutItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.TransferRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryTransferDao extends BaseMapper<TransferRecord> {
    List<TransferRecord> findTransferPage(Page<TransferRecord> page, @Param("record") TransferRecord record, @Param("orderBy") String orderBy);


    @Select("select t_store_assistant.name from t_store_assistant where t_store_assistant.user_id = #{userId}")
    String staffName(@Param("userId")Long userId);

    // 调拨 详情
    List<StorageOutItemRecord> outItemRecords(@Param("outId")Long outId);
}