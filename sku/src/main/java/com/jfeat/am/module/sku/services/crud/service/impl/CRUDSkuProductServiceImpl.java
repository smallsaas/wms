package com.jfeat.am.module.sku.services.crud.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.sku.services.persistence.dao.*;
import com.jfeat.am.module.sku.services.persistence.model.*;


import com.jfeat.am.module.sku.services.crud.service.CRUDSkuProductService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.jfeat.am.module.sku.services.crud.model.SkuProductModel;

/**
 * <p>
 * implementation
 * </p>
 * CRUDSkuProductService
 *
 * @author Code Generator
 * @since 2018-07-18
 */

@Service
public class CRUDSkuProductServiceImpl extends CRUDServiceOverModelImpl<SkuProduct, SkuProductModel> implements CRUDSkuProductService {


    @Resource
    private SkuProductMapper skuProductMapper;


    @Override
    protected BaseMapper<SkuProduct> getMasterMapper() {
        return skuProductMapper;
    }

    @Override
    protected Class<SkuProduct> masterClassName() {
        return SkuProduct.class;
    }

    @Override
    protected Class<SkuProductModel> modelClassName() {
        return SkuProductModel.class;
    }


    @Resource
    private SkuUnitMapper skuUnitMapper;

    @Deprecated
    private final static String skuUnitFieldName = "sku_id";

    private final static String skuUnitKeyName = "skuUnits";


    @Resource
    private SkuPhotoMapper skuPhotoMapper;

    @Deprecated
    private final static String idFieldName = "sku_id";

    private final static String idKeyName = "skuPhotos";

    @Resource
    private SkuTagRelationMapper skuTagRelationMapper;

    @Deprecated
    private final static String idTagName = "sku_id";

    private final static String idTagKeyName = "skuTags";


    @Resource
    SkuConditionRelationMapper skuConditionRelationMapper;

    @Deprecated
    private final static String idConditionName = "sku_id";

    private final static String idConditionKeyName = "skuConditions";

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                skuUnitKeyName

                , idKeyName,
                idTagKeyName
                ,
                idConditionKeyName

        };
    }


    @Override
    protected FIELD onSlaveFieldItem(String field) {
        if (field.compareTo(skuUnitKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(skuUnitFieldName);
            _field.setItemClassName(SkuUnit.class);
            _field.setItemMapper(skuUnitMapper);
            return _field;
        } else if (field.compareTo(idKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(idFieldName);
            _field.setItemClassName(SkuPhoto.class);
            _field.setItemMapper(skuPhotoMapper);
            return _field;
        } else if (field.compareTo(idTagKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(idTagName);
            _field.setItemClassName(SkuTagRelation.class);
            _field.setItemMapper(skuTagRelationMapper);
            return _field;
        } else if (field.compareTo(idConditionKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(idConditionName);
            _field.setItemClassName(SkuConditionRelation.class);
            _field.setItemMapper(skuConditionRelationMapper);
            return _field;
        }

        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
        };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {

        throw new BusinessException(BusinessCode.BadRequest);
    }


}


