package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.ProcurementItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryProcurementDao extends BaseMapper<ProcurementRecord> {
    List<ProcurementRecord> findProcurementPage(Page<ProcurementRecord> page,
                                                @Param("record") ProcurementRecord record,
                                                @Param("orderBy") String orderBy,
                                                @Param("waitForStorageIn")String waitForStorageIn,
                                                @Param("search")String search,
                                                @Param("status")String status,
                                                @Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime
    );


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
            "WHERE wms_procurement.id = #{procurementId} AND wms_storage_in_item.type != 'Procurement'")
    Integer sectionCount(@Param("procurementId") Long procurementId);


    // 进入的仓库的名称
    @Select("select wms_warehouse.warehouse_name from wms_warehouse where wms_warehouse.id = #{warehouseId}")
    String warehouseName(@Param("warehouseId")Long warehouseId);

    // 采购经办人
    @Select("select t_store_assistant.name from t_store_assistant where t_store_assistant.user_id = #{operatorId}")
    String operatorName(@Param("operatorId")Long operatorId);

    // 入库历史记录
    List<ProcurementItemRecord> storageInHistories(@Param("procurementId")Long procurementId);


    // 某次采购 某个 sku 入库历史记录
    @Select("SELECT wms_storage_in_item.* FROM wms_storage_in_item WHERE wms_storage_in_item.storage_in_id = #{inId} AND wms_storage_in_item.sku_id = #{skuId} AND (wms_storage_in_item.type = 'Others' or wms_storage_in_item.type = 'Closed')")
    List<StorageInItem> originItems(@Param("inId")Long inId,
                                    @Param("skuId")Long skuId,
                                    @Param("type")String type);

    //某次采购 某个 sku 入库历史数量
    @Select("SELECT SUM(transaction_quantities) as storageInCount FROM wms_storage_in_item " +
            "LEFT JOIN wms_storage_in on ( !ISNULL(wms_storage_in.procurement_id) AND wms_storage_in.id = wms_storage_in_item.storage_in_id AND wms_storage_in_item.type = 'Others' ) " +
            "LEFT JOIN wms_procurement on (wms_procurement.id = wms_storage_in.procurement_id AND wms_storage_in.transaction_type = 'Procurement') " +
            "WHERE wms_procurement.id = #{procurementId} AND wms_storage_in_item.sku_id = #{skuId}")
    Integer storageInCount(@Param("procurementId")Long procurementId,
                           @Param("skuId")Long skuId);

    //某次采购 某个 sku 未审核()入库历史数量
    @Select("SELECT SUM(transaction_quantities) as storageInCount FROM wms_storage_in_item " +
            "LEFT JOIN wms_storage_in on ( !ISNULL(wms_storage_in.procurement_id) AND wms_storage_in.id = wms_storage_in_item.storage_in_id AND wms_storage_in_item.type = 'Wait_To_Audit' ) " +
            "LEFT JOIN wms_procurement on (wms_procurement.id = wms_storage_in.procurement_id AND wms_storage_in.transaction_type = 'Procurement') " +
            "WHERE wms_procurement.id = #{procurementId} AND wms_storage_in_item.sku_id = #{skuId}")
    Integer storageInAuditCount(@Param("procurementId")Long procurementId,
                           @Param("skuId")Long skuId);

    // 某个 sku 的采购的数量
    @Select("SELECT SUM(transaction_quantities) as skuProcurementCount FROM wms_storage_in_item " +
            "where wms_storage_in_item.storage_in_id = #{procurementId} " +
            "and wms_storage_in_item.sku_id = #{skuId}" +
            " and wms_storage_in_item.type = 'Procurement'")
    Integer skuProcurementCount(@Param("procurementId")Long procurementId,
                                @Param("skuId")Long skuId);

}

