package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.RefundRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutRecord;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryRefundDao extends BaseMapper<RefundRecord> {
    List<RefundRecord> findRefundPage(Page<RefundRecord> page, @Param("record") RefundRecord record, @Param("orderBy") String orderBy);



    // 某个商品某次采购 总共入库的数目
    @Select("SELECT SUM(transaction_quantities) AS storageSum " +
            "FROM wms_storage_in_item " +
            "LEFT JOIN wms_storage_in ON wms_storage_in.id = wms_storage_in_item.storage_in_id " +
            "LEFT JOIN wms_procurement ON " +
            "(wms_procurement.id = wms_storage_in.procurement_id AND wms_storage_in.transaction_type = 'Procurement') " +
            "WHERE wms_storage_in_item.type != 'Procurement' and wms_procurement.id = #{procurementId} and wms_storage_in_item.sku_id = #{skuId}")
    Integer skuStorageInCount(@Param("procurementId") Long procurementId,
                         @Param("skuId")Long skuId);


    //查找 退货的商品
    @Select("select wms_storage_out_item.* from wms_storage_out_item left join wms_storage_out on wms_storage_out.id = wms_storage_out_item.storage_out_id where wms_storage_out.id=#{outId}")
    List<StorageOutItem> outItems(@Param("outId")Long outId);

    StorageOutItemRecord outItemRecord(@Param("outItemId")Long outItemId);

    StorageOutRecord outRecord(@Param("outId")Long outId);

    //某次采购的某个商品总以退货的数量
    Integer finishedRefundCount(@Param("skuId")Long skuId,
                                @Param("procurementId")Long procurementId);

}