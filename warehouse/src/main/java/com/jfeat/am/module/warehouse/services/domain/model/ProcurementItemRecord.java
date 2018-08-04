package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;

public class ProcurementItemRecord extends StorageInItem {

    int sectionInCount;
    int remainderCount;
    String skuName;
    String skuCode;
    String skuUnit;

    public int getSectionInCount() {
        return sectionInCount;
    }

    public void setSectionInCount(int sectionInCount) {
        this.sectionInCount = sectionInCount;
    }

    public int getRemainderCount() {
        return remainderCount;
    }

    public void setRemainderCount(int remainderCount) {
        this.remainderCount = remainderCount;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuUnit() {
        return skuUnit;
    }

    public void setSkuUnit(String skuUnit) {
        this.skuUnit = skuUnit;
    }
}