package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.StockEvaluationAdditionService;
import com.jfeat.am.module.social.services.persistence.dao.StockEvaluationAdditionMapper;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluationAddition;
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
public class StockEvaluationAdditionServiceImpl extends CRUDServiceOnlyImpl<StockEvaluationAddition> implements StockEvaluationAdditionService {


    @Resource
    private StockEvaluationAdditionMapper stockEvaluationAdditionMapper;

    @Override
    protected BaseMapper<StockEvaluationAddition> getMasterMapper() {
        return stockEvaluationAdditionMapper;
    }
}


