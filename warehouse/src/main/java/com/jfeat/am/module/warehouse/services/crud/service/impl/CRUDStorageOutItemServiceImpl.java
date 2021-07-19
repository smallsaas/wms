package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutItemMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutItemService;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStorageOutItemService
 * @author Code Generator
 * @since 2018-06-20
 */

@Service
public class CRUDStorageOutItemServiceImpl  extends CRUDServiceSlaveImpl<StorageOutItem> implements CRUDStorageOutItemService {







    private static final String masterField = "wms_storage_out_id";

    @Resource
    private StorageOutItemMapper storageOutItemMapper;

    @Override
    protected BaseMapper<StorageOutItem> getSlaveItemMapper() {
        return storageOutItemMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


