package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuTagRelation;
import com.jfeat.am.module.sku.services.persistence.dao.SkuTagRelationMapper;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuTagRelationService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuTagRelationService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuTagRelationServiceImpl extends CRUDServiceOnlyImpl<SkuTagRelation> implements CRUDSkuTagRelationService {


    @Resource
    private SkuTagRelationMapper skuTagRelationMapper;

    @Override
    protected BaseMapper<SkuTagRelation> getMasterMapper() {
        return skuTagRelationMapper;
    }


}


