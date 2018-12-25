package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Refund;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public class RefundModel extends Refund{
    List<StorageOutItem> items;
    String operatorName;
    String procurementCode;
    String supplierName;
//    List<StorageOutRecord> outRecords;
    String warehouseName;

    List<StorageOutItemRecord> itemRecords;

    public List<StorageOutItem> getItems() {
        return items;
    }

    public void setItems(List<StorageOutItem> items) {
        this.items = items;
    }


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getProcurementCode() {
        return procurementCode;
    }

    public void setProcurementCode(String procurementCode) {
        this.procurementCode = procurementCode;
    }

    /*public List<StorageOutRecord> getOutRecords() {
        return outRecords;
    }

    public void setOutRecords(List<StorageOutRecord> outRecords) {
        this.outRecords = outRecords;
    }*/

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public List<StorageOutItemRecord> getItemRecords() {
        return itemRecords;
    }

    public void setItemRecords(List<StorageOutItemRecord> itemRecords) {
        this.itemRecords = itemRecords;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
