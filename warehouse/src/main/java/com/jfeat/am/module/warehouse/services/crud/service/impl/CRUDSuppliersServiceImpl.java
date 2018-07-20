package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.module.warehouse.services.persistence.model.Suppliers;
import com.jfeat.am.module.warehouse.services.persistence.dao.SuppliersMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDSuppliersService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDSuppliersService
 * @author Code Generator
 * @since 2018-06-22
 */

@Service
public class CRUDSuppliersServiceImpl  extends CRUDServiceOnlyImpl<Suppliers> implements CRUDSuppliersService {





        @Resource
        private SuppliersMapper suppliersMapper;

        @Override
        protected BaseMapper<Suppliers> getMasterMapper() {
                return suppliersMapper;
        }







}


