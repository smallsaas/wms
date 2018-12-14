package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface StorageInService extends CRUDStorageInService{

    /**
     *
     * 假设 该提交的 SKU 不存在 库存 ，则插入 ，如果存在 ，则更新
     * */
    @Transactional
    public Integer executionStorageIn(long userId,StorageInModel entity);
}