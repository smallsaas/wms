package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuConditionRelation;
import com.jfeat.am.module.sku.services.persistence.dao.SkuConditionRelationMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuUnitMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuUnit;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuConditionRelationService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuConditionRelationService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuConditionRelationServiceImpl extends CRUDServiceOnlyImpl<SkuConditionRelation> implements CRUDSkuConditionRelationService {


    @Resource
    private SkuConditionRelationMapper skuConditionRelationMapper;

    @Override
    protected BaseMapper<SkuConditionRelation> getMasterMapper() {
        return skuConditionRelationMapper;
    }


}


