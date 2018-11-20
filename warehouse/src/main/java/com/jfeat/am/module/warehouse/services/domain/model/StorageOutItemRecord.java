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
    String skuUnit;
    String warehouseName;
    String operator;
    Integer finishedCount;
    Integer totalCount;

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

    public String getSkuUnit() {
        return skuUnit;
    }

    public void setSkuUnit(String skuUnit) {
        this.skuUnit = skuUnit;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(Integer finishedCount) {
        this.finishedCount = finishedCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
