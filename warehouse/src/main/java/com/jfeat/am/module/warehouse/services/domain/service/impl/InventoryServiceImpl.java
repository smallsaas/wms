package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.module.warehouse.services.domain.service.InventoryService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDInventoryServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("inventoryService")
public class InventoryServiceImpl extends CRUDInventoryServiceImpl implements InventoryService {

    @Resource
    InventoryMapper inventoryMapper;

    @Override
    public Integer getInventoryCount(Long warehouseId, Long skuId) {
        Integer count = inventoryMapper.getInventoryCount(warehouseId, skuId);
        return count;
    }
}
