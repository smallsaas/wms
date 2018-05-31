package com.jfeat.am.module.social.services.domain.dao;

import com.jfeat.am.module.social.services.persistence.model.StockEvaluation;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-11-11
 */
public interface QueryStockEvaluationDao extends BaseMapper<StockEvaluation> {
    List<StockEvaluation> findStockEvaluationPage(Page<StockEvaluation> page,StockEvaluation stockevaluation);
}