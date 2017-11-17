package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryStockCollectDao;
import com.jfeat.am.module.social.services.domain.service.QueryStockCollectService;
import com.jfeat.am.module.social.services.persistence.model.StockCollect;
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
public class QueryStockCollectServiceImpl implements QueryStockCollectService {

    @Resource
    QueryStockCollectDao queryStockCollectDao;

    @Override
    public List<StockCollect> findStockCollectPage(Page<StockCollect> page, StockCollect stockcollect) {
        return queryStockCollectDao.findStockCollectPage(page, stockcollect);
    }
}
