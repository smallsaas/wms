package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;

public class StorageModel {
    List<StorageInRecord> inRecords;
    List<StorageOutRecord> outRecords;


    public List<StorageInRecord> getInRecords() {
        return inRecords;
    }

    public void setInRecords(List<StorageInRecord> inRecords) {
        this.inRecords = inRecords;
    }

    public List<StorageOutRecord> getOutRecords() {
        return outRecords;
    }

    public void setOutRecords(List<StorageOutRecord> outRecords) {
        this.outRecords = outRecords;
    }
}
