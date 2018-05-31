package com.jfeat.am.module.social.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluationAddition;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryStockEvaluationAdditionService {
    List<StockEvaluationAddition> findStockEvaluationAdditionPage(Page<StockEvaluationAddition> page, StockEvaluationAddition stockevaluationaddition );
}