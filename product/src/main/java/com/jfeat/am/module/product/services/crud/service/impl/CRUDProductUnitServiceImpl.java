package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductUnit;
import com.jfeat.am.module.product.services.persistence.dao.ProductUnitMapper;
import com.jfeat.am.module.product.services.persistence.dao.ProductPhotoMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductPhoto;
import com.jfeat.am.module.product.services.persistence.dao.ProductUnitMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductUnit;


import com.jfeat.am.module.product.services.crud.service.CRUDProductUnitService;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDProductUnitService
 * @author Code Generator
 * @since 2018-06-19
 */

@Service
public class CRUDProductUnitServiceImpl  extends CRUDServiceSlaveImpl<ProductUnit> implements CRUDProductUnitService {







    private static final String masterField = "product_id";

    @Resource
    private ProductUnitMapper productUnitMapper;

    @Override
    protected BaseMapper<ProductUnit> getSlaveItemMapper() {
        return productUnitMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


