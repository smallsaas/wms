package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-20
 */
public class StorageOutItemRecord extends StorageOutItem {
    String skuName;
    String skuBarcode;
    String skuCode;
    String skuSpec;
    List<StorageOutItemRecord> storageOutItemRecords;

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuBarcode() {
        return skuBarcode;
    }

    public void setSkuBarcode(String skuBarcode) {
        this.skuBarcode = skuBarcode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuSpec() {
        return skuSpec;
    }

    public void setSkuSpec(String skuSpec) {
        this.skuSpec = skuSpec;
    }

    public List<StorageOutItemRecord> getStorageOutItemRecords() {
        return storageOutItemRecords;
    }

    public void setStorageOutItemRecords(List<StorageOutItemRecord> storageOutItemRecords) {
        this.storageOutItemRecords = storageOutItemRecords;
    }
}
