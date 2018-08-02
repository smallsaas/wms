package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDWarehouseService;
import com.jfeat.am.module.warehouse.services.domain.model.WarehouseModel;

/**
 * Created by vincent on 2017/10/19.
 */
public interface WarehouseService extends CRUDWarehouseService{



    /**
     * 包含 sku 信息 ，使用 field2 去 接受 可用的库存量
     * */
    WarehouseModel warehouseDetails(Long id);
}