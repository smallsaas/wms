package com.jfeat.am.module.sku.services.domain.model;

import com.jfeat.am.module.sku.services.persistence.model.SkuUnit;

/**
 * Created by Code Generator on 2018-07-18
 */
public class SkuUnitRecord extends SkuUnit{
    private String skuName;

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
}
