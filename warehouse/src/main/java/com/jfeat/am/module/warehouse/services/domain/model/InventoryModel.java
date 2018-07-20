package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;

import java.util.List;
/**
 * Created by Code Generator on 2018-06-23
 */
public class InventoryModel extends Inventory{
    List<SkuProduct> skus;

    public List<SkuProduct> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuProduct> skus) {
        this.skus = skus;
    }
}
