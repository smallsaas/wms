package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.StockEvaluationService;
import com.jfeat.am.module.social.services.persistence.dao.StockEvaluationMapper;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */
@Service
public class StockEvaluationServiceImpl extends CRUDServiceOnlyImpl<StockEvaluation> implements StockEvaluationService {
    @Resource
    private StockEvaluationMapper stockEvaluationMapper;

    @Override
    protected BaseMapper<StockEvaluation> getMasterMapper() {
        return stockEvaluationMapper;
    }
}




