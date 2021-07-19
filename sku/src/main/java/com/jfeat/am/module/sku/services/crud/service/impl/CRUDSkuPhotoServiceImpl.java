package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuPhoto;
import com.jfeat.am.module.sku.services.persistence.dao.SkuPhotoMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuUnitMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuUnit;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuPhotoService;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuPhotoService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuPhotoServiceImpl extends CRUDServiceSlaveImpl<SkuPhoto> implements CRUDSkuPhotoService {


    private static final String masterField = "sku_id";

    @Resource
    private SkuPhotoMapper skuPhotoMapper;

    @Override
    protected BaseMapper<SkuPhoto> getSlaveItemMapper() {
        return skuPhotoMapper;
    }

    @Override
    protected String masterFieldName() {
        return masterField;
    }
}


