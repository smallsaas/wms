package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Procurement;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public class ProcurementModel extends Procurement{

//    List<StorageInItem> items;
//

    List<ProcurementItemRecord> items;

    List<StorageInItemRecord> inHistories;


    String operatorName;
    String supplierName;



    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

   /* public List<StorageInItem> getItems() {
        return items;
    }

    public void setItems(List<StorageInItem> items) {
        this.items = items;
    }*/

    public List<ProcurementItemRecord> getItems() {
        return items;
    }

    public void setItems(List<ProcurementItemRecord> items) {
        this.items = items;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<StorageInItemRecord> getInHistories() {
        return inHistories;
    }

    public void setInHistories(List<StorageInItemRecord> inHistories) {
        this.inHistories = inHistories;
    }
}
