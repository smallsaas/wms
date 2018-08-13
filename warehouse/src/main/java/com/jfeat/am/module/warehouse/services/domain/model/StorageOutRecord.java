package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-20
 */
public class StorageOutRecord extends StorageOut{

    String warehouseName;
    String slotName;
    String operatorName;
    List<StorageOutItemRecord> storageOutItemRecords;


    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }


    public List<StorageOutItemRecord> getStorageOutItemRecords() {
        return storageOutItemRecords;
    }

    public void setStorageOutItemRecords(List<StorageOutItemRecord> storageOutItemRecords) {
        this.storageOutItemRecords = storageOutItemRecords;
    }
}
