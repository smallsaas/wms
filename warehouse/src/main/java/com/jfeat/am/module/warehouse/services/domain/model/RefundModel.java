package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Refund;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public class RefundModel extends Refund{
    String operatorName;
    String procurementCode;
    String supplierName;
//    List<StorageOutRecord> outRecords;
    String warehouseName;

    List<StorageOutItemRecord> items;



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

    public List<StorageOutItemRecord> getItems() {
        return items;
    }

    public void setItems(List<StorageOutItemRecord> items) {
        this.items = items;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
