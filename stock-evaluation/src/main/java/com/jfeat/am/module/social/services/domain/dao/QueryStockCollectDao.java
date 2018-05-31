package com.jfeat.am.module.social.services.domain.dao;

import com.jfeat.am.module.social.services.persistence.model.StockCollect;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-11-11
 */
public interface QueryStockCollectDao extends BaseMapper<StockCollect> {
    List<StockCollect> findStockCollectPage(Page<StockCollect> page,StockCollect stockcollect);
}