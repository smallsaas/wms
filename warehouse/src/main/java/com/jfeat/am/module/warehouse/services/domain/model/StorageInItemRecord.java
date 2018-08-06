package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;

import java.util.Date;

/**
 * Created by Code Generator on 2018-06-21
 */
public class StorageInItemRecord extends StorageInItem{
    String skuName;
    String skuBarcode;
    String skuCode;

    // TODO skuSpec need more details
    String skuSpec;

    String skuUnit;

    String warehouseName;
    String storageInCode;
    String storageInNote;
    String transactionName;
    String procurementCode;
    String buyer;
    Date procurementDate;

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

    public String getStorageInCode() {
        return storageInCode;
    }

    public void setStorageInCode(String storageInCode) {
        this.storageInCode = storageInCode;
    }

    public String getStorageInNote() {
        return storageInNote;
    }

    public void setStorageInNote(String storageInNote) {
        this.storageInNote = storageInNote;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getProcurementCode() {
        return procurementCode;
    }

    public void setProcurementCode(String procurementCode) {
        this.procurementCode = procurementCode;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Date getProcurementDate() {
        return procurementDate;
    }

    public void setProcurementDate(Date procurementDate) {
        this.procurementDate = procurementDate;
    }
}
