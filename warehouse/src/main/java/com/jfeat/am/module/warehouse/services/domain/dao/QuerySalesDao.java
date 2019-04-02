package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
     *  total count
     * */
    Integer totalSkuCount(@Param("salesId")Long salesId,
                       @Param("skuId")Long skuId);
    /**
     * finishedCount
     * */
    Integer finishedCount(@Param("salesId")Long salesId,
                          @Param("skuId")Long skuId);
}