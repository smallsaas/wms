package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDInventoryService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface InventoryService extends CRUDInventoryService{

    Integer getInventoryCount(Long warehouseId, Long skuId);
}