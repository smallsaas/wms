package com.jfeat.am.module.evaluation.services.domain.dao;

import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockEvaluationDao extends BaseMapper<StockEvaluationRecord> {
    List<StockEvaluationRecord> findStockEvaluationPage(Page<StockEvaluationRecord> page, @Param("record") StockEvaluationRecord record, @Param("orderBy") String orderBy);
}