package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import com.jfeat.am.module.warehouse.services.persistence.model.Transfer;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public class TransferModel extends Transfer{
    List<StorageInItem> inItems;
    List<StorageOutItem> outItems;
    String originatorName;
    String operatorName;

    String fromWarehouseName;
    String toWarehouseName;


    public List<StorageInItem> getInItems() {
        return inItems;
    }

    public void setInItems(List<StorageInItem> inItems) {
        this.inItems = inItems;
    }

    public List<StorageOutItem> getOutItems() {
        return outItems;
    }

    public void setOutItems(List<StorageOutItem> outItems) {
        this.outItems = outItems;
    }

    public String getOriginatorName() {
        return originatorName;
    }

    public void setOriginatorName(String originatorName) {
        this.originatorName = originatorName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getFromWarehouseName() {
        return fromWarehouseName;
    }

    public void setFromWarehouseName(String fromWarehouseName) {
        this.fromWarehouseName = fromWarehouseName;
    }

    public String getToWarehouseName() {
        return toWarehouseName;
    }

    public void setToWarehouseName(String toWarehouseName) {
        this.toWarehouseName = toWarehouseName;
    }
}