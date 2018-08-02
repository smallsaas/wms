package com.jfeat.am.module.sku.services.domain.dao;

import com.jfeat.am.module.sku.services.domain.model.SkuProductRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public interface QuerySkuProductDao extends BaseMapper<SkuProductRecord> {
    List<SkuProductRecord> findSkuProductPage(Page<SkuProductRecord> page,
                                              @Param("record") SkuProductRecord record,
                                              @Param("orderBy") String orderBy,
                                              @Param("tagName") String tagName,
                                              @Param("specName") String specName,
                                              @Param("categoryName") String categoryName,
                                              @Param("warehouseName") String warehouseName);
}