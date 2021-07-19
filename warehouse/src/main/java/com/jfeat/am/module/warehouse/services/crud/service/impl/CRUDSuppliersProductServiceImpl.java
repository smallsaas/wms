package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.SuppliersProduct;
import com.jfeat.am.module.warehouse.services.persistence.dao.SuppliersProductMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDSuppliersProductService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDSuppliersProductService
 * @author Code Generator
 * @since 2018-06-30
 */

@Service
public class CRUDSuppliersProductServiceImpl  extends CRUDServiceOnlyImpl<SuppliersProduct> implements CRUDSuppliersProductService {





        @Resource
        private SuppliersProductMapper suppliersProductMapper;

        @Override
        protected BaseMapper<SuppliersProduct> getMasterMapper() {
                return suppliersProductMapper;
        }







}


