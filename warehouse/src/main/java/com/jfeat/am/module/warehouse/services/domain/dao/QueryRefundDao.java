package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.RefundRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutRecord;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryRefundDao extends BaseMapper<RefundRecord> {
    List<RefundRecord> findRefundPage(Page<RefundRecord> page,
                                      @Param("record") RefundRecord record,
                                      @Param("orderBy") String orderBy,
                                      @Param("search")String search,
                                      @Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime);




    //查找 退货的商品
    @Select("select wms_storage_out_item.* from wms_storage_out_item left join wms_storage_out on wms_storage_out.id = wms_storage_out_item.storage_out_id where wms_storage_out.id=#{outId}")
    List<StorageOutItem> outItems(@Param("outId")Long outId);

    StorageOutItemRecord outItemRecord(@Param("outItemId")Long outItemId);

    StorageOutRecord outRecord(@Param("outId")Long outId);

    //某次采购的某个商品总以退货的数量
    Integer finishedRefundCount(@Param("skuId")Long skuId,
                                @Param("procurementId")Long procurementId);

}