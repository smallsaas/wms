package com.jfeat.am.module.warehouse.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.plus.*;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.crud.plus.model.IdVersions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;

import java.util.List;

/**
 * <p>
 * implementation
 * </p>
 * CRUDStorageInService
 *
 * @author Code Generator
 * @since 2018-06-21
 */

@Service
public class CRUDStorageInServiceImpl extends CRUDServiceOverModelImpl<StorageIn, StorageInModel> implements CRUDStorageInService {


    @Resource
    private StorageInMapper storageInMapper;


    @Override
    protected BaseMapper<StorageIn> getMasterMapper() {
        return storageInMapper;
    }

    @Override
    protected Class<StorageIn> masterClassName() {
        return StorageIn.class;
    }

    @Override
    protected Class<StorageInModel> modelClassName() {
        return StorageInModel.class;
    }


    @Resource
    private StorageInItemMapper storageInItemMapper;

    @Deprecated
    private final static String storageInItemFieldName = "storage_in_id";

    private final static String storageInItemKeyName = "storageInItems";

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                storageInItemKeyName

        };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {
        if (field.compareTo(storageInItemKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(storageInItemFieldName);
            _field.setItemClassName(StorageInItem.class);
            _field.setItemMapper(storageInItemMapper);
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


