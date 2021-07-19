package com.jfeat.am.module.sku.services.domain.dao;

import com.jfeat.am.module.sku.services.domain.model.SkuPriceHistoryRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public interface QuerySkuPriceHistoryDao extends BaseMapper<SkuPriceHistoryRecord> {
    List<SkuPriceHistoryRecord> findSkuPriceHistoryPage(Page<SkuPriceHistoryRecord> page, @Param("record") SkuPriceHistoryRecord record, @Param("orderBy") String orderBy);
}