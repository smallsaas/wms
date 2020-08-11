package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuCondition;
import com.jfeat.am.module.sku.services.persistence.dao.SkuConditionMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuUnitMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuUnit;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuConditionService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuConditionService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuConditionServiceImpl extends CRUDServiceOnlyImpl<SkuCondition> implements CRUDSkuConditionService {


    @Resource
    private SkuConditionMapper skuConditionMapper;

    @Override
    protected BaseMapper<SkuCondition> getMasterMapper() {
        return skuConditionMapper;
    }


}


