package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface StorageOutService extends CRUDStorageOutService{

    /**
     * while create , storage out  status is Draft;
     */
    Integer draftStorageOut(Long userId, StorageOutModel entity);

    /**
     * update by do not modified status
     */
    @Transactional
    Integer updateStorageIn(Long userId, Long storageOutId, StorageOutModel entity);

    /**
     * commit and wait to audit
     */
    @Transactional
    Integer commitStorageOut(Long userId, Long storageOutId, StorageOutModel entity);

    /**
     * audit passed
     */
    @Transactional
    Integer passedStorageOut(Long storageOutId);

    /**
     * audit rejected
     */
    @Transactional
    Integer auditRejectedStorageOut(Long storageOutId);


    /**
     * SKU 肯定 存在  需要 判断 他的 可用量 是否 大于 出库的数量
     */
    @Transactional
    Integer executionStorageOut(String uasername, Long storageOutId);


    /**
     *
     *  for 商城下单 出库，不需要审核直接出库
     * */
    @Transactional
    Integer salesStorageOut(Long userId, StorageOutModel entity);
}