package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDWarehouseService;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryStorageInDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryStorageOutDao;
import com.jfeat.am.module.warehouse.services.domain.model.WarehouseModel;
import com.jfeat.am.module.warehouse.services.domain.service.WarehouseService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDWarehouseServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.Warehouse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("warehouseService")
public class WarehouseServiceImpl extends CRUDWarehouseServiceImpl implements WarehouseService {

    @Resource
    CRUDWarehouseService crudWarehouseService;
    @Resource
    SkuProductMapper skuProductMapper;
    @Resource
    InventoryMapper inventoryMapper;


    /**
     * 包含 sku 信息 ，使用 field2 去 接受 可用的库存量
     * */
    public WarehouseModel warehouseDetails(Long id) {

        JSONObject warehouse = crudWarehouseService.retrieveMaster(id, null, null, null).toJSONObject();

        List<Inventory> inventories = inventoryMapper.selectList(new EntityWrapper<Inventory>().eq(Inventory.WAREHOUSE_ID, id));

        List<SkuProduct> skus = new ArrayList<>();
        if (inventories != null && inventories.size() > 0) {

            for (Inventory inventory : inventories) {
                SkuProduct skuProduct = skuProductMapper.selectById(inventory.getSkuId());
                skuProduct.setField2(String.valueOf(inventory.getValidSku()));
                skus.add(skuProduct);
            }
        }

        warehouse.put("skus",skus==null?null:skus);
        WarehouseModel model = JSON.parseObject(JSON.toJSONString(warehouse),WarehouseModel.class);
        return model;

    }
}
