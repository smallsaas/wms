package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryStockEvaluationDao;
import com.jfeat.am.module.social.services.domain.service.QueryStockEvaluationService;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QueryStockEvaluationServiceImpl implements QueryStockEvaluationService {

    @Resource
    QueryStockEvaluationDao queryStockEvaluationDao;

    @Override
    public List<StockEvaluation> findStockEvaluationPage(Page<StockEvaluation> page, StockEvaluation stockevaluation) {
        return queryStockEvaluationDao.findStockEvaluationPage(page, stockevaluation);
    }
}
