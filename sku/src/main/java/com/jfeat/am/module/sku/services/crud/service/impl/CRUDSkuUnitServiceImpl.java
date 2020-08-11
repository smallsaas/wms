package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuUnit;
import com.jfeat.am.module.sku.services.persistence.dao.SkuUnitMapper;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuUnitService;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuUnitService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuUnitServiceImpl extends CRUDServiceSlaveImpl<SkuUnit> implements CRUDSkuUnitService {


    private static final String masterField = "sku_id";

    @Resource
    private SkuUnitMapper skuUnitMapper;

    @Override
    protected BaseMapper<SkuUnit> getSlaveItemMapper() {
        return skuUnitMapper;
    }

    @Override
    protected String masterFieldName() {
        if (true) {
            throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }


}


