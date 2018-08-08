package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Refund;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public class RefundModel extends Refund{
    List<StorageOutItem> items;
    String originatorName;
    String operatorName;
    String procurementCode;
    List<StorageOutRecord> outRecords;

    public List<StorageOutItem> getItems() {
        return items;
    }

    public void setItems(List<StorageOutItem> items) {
        this.items = items;
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

    public String getProcurementCode() {
        return procurementCode;
    }

    public void setProcurementCode(String procurementCode) {
        this.procurementCode = procurementCode;
    }

    public List<StorageOutRecord> getOutRecords() {
        return outRecords;
    }

    public void setOutRecords(List<StorageOutRecord> outRecords) {
        this.outRecords = outRecords;
    }
}
