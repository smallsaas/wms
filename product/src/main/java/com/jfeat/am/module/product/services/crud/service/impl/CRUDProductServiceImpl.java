package com.jfeat.am.module.product.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.product.services.crud.service.CRUDProductService;
import com.jfeat.am.module.product.services.domain.model.ProductModel;
import com.jfeat.am.module.product.services.persistence.dao.ProductConditionMapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductMapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductPhotoMapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductUnitMapper;
import com.jfeat.am.module.product.services.persistence.model.Product;
import com.jfeat.am.module.product.services.persistence.model.ProductCondition;
import com.jfeat.am.module.product.services.persistence.model.ProductPhoto;
import com.jfeat.am.module.product.services.persistence.model.ProductUnit;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.plus.*;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.crud.plus.model.IdVersions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * implementation
 * </p>
 * CRUDProductService
 *
 * @author Code Generator
 * @since 2018-06-19
 */

@Service
public class CRUDProductServiceImpl extends CRUDServiceOverModelImpl<Product, ProductModel> implements CRUDProductService {


    @Resource
    private ProductMapper productMapper;


    @Override
    protected BaseMapper<Product> getMasterMapper() {
        return productMapper;
    }

    @Override
    protected Class<Product> masterClassName() {
        return Product.class;
    }

    @Override
    protected Class<ProductModel> modelClassName() {
        return ProductModel.class;
    }


    @Resource
    private ProductPhotoMapper productPhotoMapper;

    @Deprecated
    private final static String productPhotoFieldName = "product_id";

    private final static String productPhotoKeyName = "productPhotos";


    @Resource
    private ProductUnitMapper productUnitMapper;

    @Deprecated
    private final static String productUnitFieldName = "product_id";

    private final static String productUnitKeyName = "productUnits";


    @Resource
    private ProductConditionMapper productConditionMapper;


    private final static String productConditionFieldName = "product_id";

    private final static String productConditionKeyName = "productConditions";


    /*@Resource
    private ProductTagRelationMapper productTagRelationMapper;*/


    private final static String productTagRelationFieldName = "product_id";

    private final static String productTagRelationKeyName = "productTagRelations";

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                productPhotoKeyName

                , productUnitKeyName,

                productConditionKeyName,

                productTagRelationKeyName

        };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {
        if (field.compareTo(productPhotoKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(productPhotoFieldName);
            _field.setItemClassName(ProductPhoto.class);
            _field.setItemMapper(productPhotoMapper);
            return _field;
        } else if (field.compareTo(productUnitKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(productUnitFieldName);
            _field.setItemClassName(ProductUnit.class);
            _field.setItemMapper(productUnitMapper);
            return _field;
        } else if (field.compareTo(productConditionKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(productConditionFieldName);
            _field.setItemClassName(ProductCondition.class);
            _field.setItemMapper(productConditionMapper);
            return _field;
        } else if (field.compareTo(productTagRelationKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(productTagRelationFieldName);
            /*_field.setItemClassName(ProductTagRelation.class);
            _field.setItemMapper(productTagRelationMapper);*/
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


