package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.StockCollectService;
import com.jfeat.am.module.social.services.persistence.dao.StockCollectMapper;
import com.jfeat.am.module.social.services.persistence.model.StockCollect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by J4cob on 2017/11/9.
 */
@Service
public class StockCollectServiceImpl extends CRUDServiceOnlyImpl<StockCollect> implements StockCollectService {
    @Resource
    private StockCollectMapper stockCollectMapper;

    @Override
    protected BaseMapper<StockCollect> getMasterMapper() {
        return stockCollectMapper;
    }
}
