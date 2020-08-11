package com.jfeat.am.module.warehouse.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutItemMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;

/**
 * <p>
 * implementation
 * </p>
 * CRUDStorageOutService
 *
 * @author Code Generator
 * @since 2018-06-20
 */

@Service
public class CRUDStorageOutServiceImpl extends CRUDServiceOverModelImpl<StorageOut, StorageOutModel> implements CRUDStorageOutService {


    @Resource
    private StorageOutMapper storageOutMapper;


    @Override
    protected BaseMapper<StorageOut> getMasterMapper() {
        return storageOutMapper;
    }

    @Override
    protected Class<StorageOut> masterClassName() {
        return StorageOut.class;
    }

    @Override
    protected Class<StorageOutModel> modelClassName() {
        return StorageOutModel.class;
    }


    @Resource
    private StorageOutItemMapper storageOutItemMapper;

    @Deprecated
    private final static String storageOutItemFieldName = "storage_out_id";

    private final static String storageOutItemKeyName = "storageOutItems";

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                storageOutItemKeyName

        };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {
        if (field.compareTo(storageOutItemKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(storageOutItemFieldName);
            _field.setItemClassName(StorageOutItem.class);
            _field.setItemMapper(storageOutItemMapper);
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


