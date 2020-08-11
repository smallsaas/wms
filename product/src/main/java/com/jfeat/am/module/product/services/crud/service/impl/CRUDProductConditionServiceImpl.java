package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.product.services.persistence.model.Condition;
import com.jfeat.am.module.product.services.persistence.dao.ProductConditionMapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductMapper;
import com.jfeat.am.module.product.services.persistence.dao.ConditionMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductCondition;
import com.jfeat.am.module.product.services.persistence.dao.ProductConditionMapper;


import com.jfeat.am.module.product.services.crud.service.CRUDProductConditionService;
import com.jfeat.crud.plus.impl.CRUDServicePeerImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDProductConditionService
 * @author Code Generator
 * @since 2018-06-19
 */

@Service
public class CRUDProductConditionServiceImpl  extends CRUDServicePeerImpl<Product,Condition,ProductCondition> implements CRUDProductConditionService {


    @Resource
    private ProductMapper masterMapper;

    @Resource
    private ConditionMapper masterPeerMapper;

    @Resource
    private ProductConditionMapper relationMapper;


    @Override
    protected BaseMapper<Product> getMasterMapper() {
            return masterMapper;
    }

    @Override
    protected BaseMapper<Condition> getMasterPeerMapper() {
            return masterPeerMapper;
    }

    @Override
    protected BaseMapper<ProductCondition> getRelationMapper() {
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


