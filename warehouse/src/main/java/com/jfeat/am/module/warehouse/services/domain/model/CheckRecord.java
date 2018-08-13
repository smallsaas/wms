package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Check;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-23
 */
public class CheckRecord extends Check{

    List<CheckSkuRecord> skuRecords;
    String warehouseName;
    String originatorName;


    public List<CheckSkuRecord> getSkuRecords() {
        return skuRecords;
    }

    public void setSkuRecords(List<CheckSkuRecord> skuRecords) {
        this.skuRecords = skuRecords;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getOriginatorName() {
        return originatorName;
    }

    public void setOriginatorName(String originatorName) {
        this.originatorName = originatorName;
    }
}
