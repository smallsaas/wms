package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.persistence.model.Check;
import com.jfeat.am.module.warehouse.services.persistence.dao.CheckSkuMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.CheckMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.CheckSku;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDCheckSkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServicePeerImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDCheckSkuService
 * @author Code Generator
 * @since 2018-06-23
 */

@Service
public class CRUDCheckSkuServiceImpl  extends CRUDServicePeerImpl<Check,SkuProduct,CheckSku> implements CRUDCheckSkuService {


    @Resource
    private CheckMapper masterMapper;

    @Resource
    private SkuProductMapper masterPeerMapper;

    @Resource
    private CheckSkuMapper relationMapper;


    @Override
    protected BaseMapper<Check> getMasterMapper() {
            return masterMapper;
    }

    @Override
    protected BaseMapper<SkuProduct> getMasterPeerMapper() {
            return masterPeerMapper;
    }

    @Override
    protected BaseMapper<CheckSku> getRelationMapper() {
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


