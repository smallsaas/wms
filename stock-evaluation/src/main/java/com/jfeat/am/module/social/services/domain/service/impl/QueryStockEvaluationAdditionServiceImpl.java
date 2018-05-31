package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryStockEvaluationAdditionDao;
import com.jfeat.am.module.social.services.domain.service.QueryStockEvaluationAdditionService;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluationAddition;
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
public class QueryStockEvaluationAdditionServiceImpl implements QueryStockEvaluationAdditionService {

    @Resource
    QueryStockEvaluationAdditionDao queryStockEvaluationAdditionDao;

    @Override
    public List<StockEvaluationAddition> findStockEvaluationAdditionPage(Page<StockEvaluationAddition> page, StockEvaluationAddition stockevaluationaddition) {
        return queryStockEvaluationAdditionDao.findStockEvaluationAdditionPage(page, stockevaluationaddition);
    }
}
