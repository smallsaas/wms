package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.common.crud.impl.CRUDServiceSlaveImpl;
import com.jfeat.am.module.sku.services.persistence.model.SkuPhoto;
import com.jfeat.am.module.sku.services.persistence.dao.SkuPhotoMapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuUnitMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuUnit;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuPhotoService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;

import javax.annotation.Resource;

import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;

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
        if (true) {
            throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }


}


