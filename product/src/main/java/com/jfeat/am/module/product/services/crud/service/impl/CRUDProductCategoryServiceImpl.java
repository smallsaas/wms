package com.jfeat.am.module.product.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.product.services.persistence.model.ProductCategory;
import com.jfeat.am.module.product.services.persistence.dao.ProductCategoryMapper;


import com.jfeat.am.module.product.services.crud.service.CRUDProductCategoryService;
import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.crud.plus.model.IdVersions;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDProductCategoryService
 * @author Code Generator
 * @since 2018-06-19
 */

@Service
public class CRUDProductCategoryServiceImpl  extends CRUDServiceOnlyImpl<ProductCategory> implements CRUDProductCategoryService {





        @Resource
        private ProductCategoryMapper productCategoryMapper;

        @Override
        protected BaseMapper<ProductCategory> getMasterMapper() {
                return productCategoryMapper;
        }

}


