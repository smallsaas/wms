package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDStorageOutServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("crudStorageOutService")
public class StorageOutServiceImpl extends CRUDStorageOutServiceImpl implements StorageOutService {


    @Resource
    CRUDStorageOutService crudStorageOutService;
    @Resource
    InventoryMapper inventoryMapper;

    /**
     * SKU 肯定 存在  需要 判断 他的 可用量 是否 大于 出库的数量
     */
    @Transactional
    public Integer createStorageOut(long userId, StorageOutModel entity) {
        Integer affected = 0;
        entity.setOriginatorId(userId);
        if (entity.getTransactionBy() == null) {
            entity.setTransactionBy(userId);
        }
        entity.setTransactionTime(new Date());


        return affected;
    }
}
