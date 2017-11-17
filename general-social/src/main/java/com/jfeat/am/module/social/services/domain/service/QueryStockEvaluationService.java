package com.jfeat.am.module.social.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluation;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryStockEvaluationService {
    List<StockEvaluation> findStockEvaluationPage(Page<StockEvaluation> page, StockEvaluation stockevaluation );
}