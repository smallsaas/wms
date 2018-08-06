package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.ProcurementItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryProcurementDao extends BaseMapper<ProcurementRecord> {
    List<ProcurementRecord> findProcurementPage(Page<ProcurementRecord> page, @Param("record") ProcurementRecord record, @Param("orderBy") String orderBy);


    // 入库总数目 ，原入库总数目
    @Select("SELECT SUM(transaction_quantities)  as totalCount " +
            "FROM wms_storage_in_item " +
            "LEFT JOIN wms_procurement on " +
            "(wms_procurement.id = wms_storage_in_item.storage_in_id AND wms_storage_in_item.type = 'Procurement') " +
            "where wms_procurement.id = #{procurementId}")
    Integer totalCount(@Param("procurementId") Long procurementId);

    // 已 入库的数目
    @Select("SELECT SUM(transaction_quantities) AS sectionCount " +
            "FROM wms_storage_in_item " +
            "LEFT JOIN wms_storage_in ON wms_storage_in.id = wms_storage_in_item.storage_in_id " +
            "LEFT JOIN wms_procurement ON " +
            "(wms_procurement.id = wms_storage_in.procurement_id AND wms_storage_in.transaction_type = 'Procurement') " +
            "WHERE wms_procurement.id = #{procurementId}")
    Integer sectionCount(@Param("procurementId") Long procurementId);


    // 入库经办人
    @Select("select t_store_assistant.name from t_store_assistant where t_store_assistant.user_id = #{userId}")
    String transactionName(@Param("userId")Long userId);
    // 进入的仓库的名称
    @Select("select wms_warehouse.warehouse_name from wms_warehouse where wms_warehouse.id = #{warehouseId}")
    String warehouseName(@Param("warehouseId")Long warehouseId);

    //采购制单人
    @Select("select t_store_assistant.name from t_store_assistant where t_store_assistant.user_id = #{originatorId}")
    String originatorName(@Param("originatorId")Long originatorId);

    // 采购经办人
    @Select("select t_store_assistant.name from t_store_assistant where t_store_assistant.user_id = #{operatorId}")
    String operatorName(@Param("operatorId")Long operatorId);

    // 入库历史记录
    List<ProcurementItemRecord> storageInHistories(@Param("procurementId")Long procurementId);


}