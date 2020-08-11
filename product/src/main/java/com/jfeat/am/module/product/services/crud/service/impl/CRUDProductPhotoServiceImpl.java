package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductPhoto;
import com.jfeat.am.module.product.services.persistence.dao.ProductPhotoMapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductPhotoMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductPhoto;
import com.jfeat.am.module.product.services.persistence.dao.ProductUnitMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductUnit;


import com.jfeat.am.module.product.services.crud.service.CRUDProductPhotoService;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDProductPhotoService
 * @author Code Generator
 * @since 2018-06-19
 */

@Service
public class CRUDProductPhotoServiceImpl  extends CRUDServiceSlaveImpl<ProductPhoto> implements CRUDProductPhotoService {







    private static final String masterField = "product_id";

    @Resource
    private ProductPhotoMapper productPhotoMapper;

    @Override
    protected BaseMapper<ProductPhoto> getSlaveItemMapper() {
        return productPhotoMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


