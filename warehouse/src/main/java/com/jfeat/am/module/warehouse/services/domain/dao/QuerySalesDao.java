package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Code Generator on 2018-11-14
 */
public interface QuerySalesDao extends BaseMapper<SalesRecord> {
    List<SalesRecord> findSalesPage(Page<SalesRecord> page,
                                    @Param("traderName")String traderName,
                                    @Param("record") SalesRecord record,
                                    @Param("orderBy") String orderBy,
                                    @Param("startTime") Date startTime,
                                    @Param("endTime") Date endTime);

    Integer skuSalesCount(@Param("salesId") Long salesId,
                          @Param("skuId") Long skuId);

    Integer skuSalesOutCount(@Param("salesId") Long salesId,
                             @Param("skuId") Long skuId);


    SalesDetails salesDetails(@Param("id")Long id);

    StorageOutItemRecord itemRecords(@Param("id")Long id);

    StorageOutRecord storagesOutDetails(@Param("id")Long id);

    /**
     *  单个 sku total count
     * */
    Integer totalSkuCount(@Param("salesId")Long salesId,
                       @Param("skuId")Long skuId);
    /**
     * 单个 sku finishedCount
     * */
    Integer finishedCount(@Param("salesId")Long salesId,
                          @Param("skuId")Long skuId);
    /**
     * 审核通过但是没入库的数量
     * */
    Integer auditStorageOutPass(@Param("salesId")Long salesId,
                          @Param("skuId")Long skuId);
    /**
     * 待审核的出库数
     * */
    Integer auditStorageOutCount(@Param("salesId")Long salesId,
                          @Param("skuId")Long skuId);

    Integer totalCount(@Param("salesId") Long salesId);

    /**
     * 单个 sku finishedCount
     * */
    Integer finishedTotalCount(@Param("salesId")Long salesId);

}