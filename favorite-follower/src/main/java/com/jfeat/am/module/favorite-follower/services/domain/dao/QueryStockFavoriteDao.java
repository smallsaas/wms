package com.jfeat.am.module.product.services.domain.dao;

import com.jfeat.am.module.product.services.domain.model.record.StockFavoriteRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockFavoriteDao extends BaseMapper<StockFavoriteRecord> {
    List<StockFavoriteRecord> findStockFavoritePage(Page<StockFavoriteRecord> page, @Param("record") StockFavoriteRecord record, @Param("orderBy") String orderBy);
}