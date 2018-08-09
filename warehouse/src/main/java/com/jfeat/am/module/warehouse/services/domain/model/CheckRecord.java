package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Check;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-23
 */
public class CheckRecord extends Check{

    List<CheckSkuRecord> skuRecords;


    public List<CheckSkuRecord> getSkuRecords() {
        return skuRecords;
    }

    public void setSkuRecords(List<CheckSkuRecord> skuRecords) {
        this.skuRecords = skuRecords;
    }
}
