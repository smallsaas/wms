package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Transfer;

/**
 * Created by Code Generator on 2018-06-22
 */
public class TransferRecord extends Transfer{


    String fromWarehouseName;
    String originatorName;
    String operatorName;
    String toWarehouseName;


    public String getFromWarehouseName() {
        return fromWarehouseName;
    }

    public void setFromWarehouseName(String fromWarehouseName) {
        this.fromWarehouseName = fromWarehouseName;
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

    public String getToWarehouseName() {
        return toWarehouseName;
    }

    public void setToWarehouseName(String toWarehouseName) {
        this.toWarehouseName = toWarehouseName;
    }
}
