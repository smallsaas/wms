package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceSlaveImpl;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInItemMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStorageInItemService
 * @author Code Generator
 * @since 2018-06-21
 */

@Service
public class CRUDStorageInItemServiceImpl  extends CRUDServiceSlaveImpl<StorageInItem> implements CRUDStorageInItemService {







    private static final String masterField = "storage_in_id";

    @Resource
    private StorageInItemMapper storageInItemMapper;

    @Override
    protected BaseMapper<StorageInItem> getSlaveItemMapper() {
        return storageInItemMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


