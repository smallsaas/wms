package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;

/**
 * Created by Code Generator on 2018-06-23
 */
public class InventoryRecord extends Inventory{


    String skuName;
    String warehouseName;

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
