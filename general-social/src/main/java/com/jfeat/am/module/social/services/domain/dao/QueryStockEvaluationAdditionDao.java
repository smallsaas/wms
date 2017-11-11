package com.jfeat.am.module.social.services.domain.dao;

import com.jfeat.am.module.social.services.persistence.model.StockEvaluationAddition;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-11-11
 */
public interface QueryStockEvaluationAdditionDao extends BaseMapper<StockEvaluationAddition> {
    List<StockEvaluationAddition> findStockEvaluationAdditionPage(Page<StockEvaluationAddition> page,StockEvaluationAddition stockevaluationaddition);
}