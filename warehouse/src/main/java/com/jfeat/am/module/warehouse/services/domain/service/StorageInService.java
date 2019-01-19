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
    Integer createStorageIn(Long userId, StorageInModel entity);

    /**
     *  update by do not modified status
     * */
    @Transactional
    Integer updateStorageIn(Long userId,Long storageInId,StorageInModel entity);

    /**
     *  commit and wait to audit
     * */
    @Transactional
    Integer commitStorageIn(Long userId,Long storageInId,StorageInModel entity);

    /**
     *  audit passed
     * */
    @Transactional
    Integer passedStorageIn(Long storageInId,StorageInModel entity);


    /**
     *  audit rejected
     * */
    @Transactional
    Integer auditRejectedStorageIn(Long storageInId);


    /**
     *
     * 假设 该提交的 SKU 不存在 库存 ，则插入 ，如果存在 ，则更新
     * */
    @Transactional
    Integer executionStorageIn(String username,Long storageInId);


    /**
     *  for 商城退货的时候直接 插入，不许需要审核
     * */
    @Transactional
    Integer salesStorageIn(Long userId, StorageInModel entity);


}