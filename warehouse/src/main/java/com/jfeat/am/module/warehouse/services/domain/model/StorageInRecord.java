package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-21
 */
public class StorageInRecord extends StorageIn{
    String warehouseName;
    String slotName;

    String operatorName;
    List<StorageInItemRecord> storageInItems;
    String procurementCode ;


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


    public List<StorageInItemRecord> getStorageInItems() {
        return storageInItems;
    }

    public void setStorageInItems(List<StorageInItemRecord> storageInItems) {
        this.storageInItems = storageInItems;
    }

    public String getProcurementCode() {
        return procurementCode;
    }

    public void setProcurementCode(String procurementCode) {
        this.procurementCode = procurementCode;
    }
}
