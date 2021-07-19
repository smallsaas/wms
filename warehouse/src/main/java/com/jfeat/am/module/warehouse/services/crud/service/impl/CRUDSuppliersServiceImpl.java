package com.jfeat.am.module.warehouse.services.crud.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Suppliers;
import com.jfeat.am.module.warehouse.services.persistence.dao.SuppliersMapper;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDSuppliersService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * implementation
 * </p>
 * CRUDSuppliersService
 *
 * @author Code Generator
 * @since 2018-06-22
 */

@Service
public class CRUDSuppliersServiceImpl extends CRUDServiceOnlyImpl<Suppliers> implements CRUDSuppliersService {

    @Resource
    private SuppliersMapper suppliersMapper;

    @Override
    protected BaseMapper<Suppliers> getMasterMapper() {
        return suppliersMapper;
    }


}


