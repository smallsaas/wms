package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface StorageInService extends CRUDStorageInService{


    /**
     *  while create , storage in  status is Draft;
     * */
    @Transactional
    Integer createStorageIn(long userId, StorageInModel entity);

    /**
     *  update by do not modified status
     * */
    @Transactional
    Integer updateStorageIn(long userId,Long storageInId,StorageInModel entity);

    /**
     *  commit and wait to audit
     * */
    @Transactional
    Integer commitStorageIn(long userId,Long storageInId,StorageInModel entity);

    /**
     *  audit passed
     * */
    @Transactional
    Integer passedStorageIn(long userId,Long storageInId,StorageInModel entity);


    /**
     *  audit rejected
     * */
    @Transactional
    Integer auditRejectedStorageIn(long userId,Long storageInId,StorageInModel entity);


    /**
     *
     * 假设 该提交的 SKU 不存在 库存 ，则插入 ，如果存在 ，则更新
     * */
    @Transactional
    Integer executionStorageIn(long userId,StorageInModel entity);


}