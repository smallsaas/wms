package com.jfeat.am.module.sku.services.crud.model;

import com.jfeat.am.module.sku.services.persistence.model.SkuUnit;

/**
 * Created by Code Generator on 2018-07-18
 */
public class SkuUnitModel extends SkuUnit{

    private String skuIdString;
    private String skuName;

    public String getSkuIdString() {
        return skuIdString;
    }

    public void setSkuIdString(String skuIdString) {
        this.skuIdString = skuIdString;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
}
