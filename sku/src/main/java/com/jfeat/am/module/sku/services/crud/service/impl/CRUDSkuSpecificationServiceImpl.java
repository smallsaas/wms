package com.jfeat.am.module.sku.services.crud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.sku.services.persistence.model.SkuSpecificationGroup;
import com.jfeat.am.module.sku.services.persistence.dao.SkuSpecificationMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuSpecificationGroupMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuSpecification;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuSpecificationService;
import com.jfeat.crud.plus.impl.CRUDServicePeerImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuSpecificationService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuSpecificationServiceImpl extends CRUDServicePeerImpl<SkuProduct, SkuSpecificationGroup, SkuSpecification> implements CRUDSkuSpecificationService {


    @Resource
    private SkuProductMapper masterMapper;

    @Resource
    private SkuSpecificationGroupMapper masterPeerMapper;

    @Resource
    private SkuSpecificationMapper relationMapper;


    @Override
    protected BaseMapper<SkuProduct> getMasterMapper() {
        return masterMapper;
    }

    @Override
    protected BaseMapper<SkuSpecificationGroup> getMasterPeerMapper() {
        return masterPeerMapper;
    }

    @Override
    protected BaseMapper<SkuSpecification> getRelationMapper() {
        return relationMapper;
    }

    @Override
    protected String[] relationMatches() {
        return new String[0];
    }


    public JSONObject getRelationData(long l, long l1) {
        return null;
    }
}


