package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;

public class SalesDetails extends SalesRecord{

    List<StorageOutRecord> outRecords;


    public List<StorageOutRecord> getOutRecords() {
        return outRecords;
    }

    public void setOutRecords(List<StorageOutRecord> outRecords) {
        this.outRecords = outRecords;
    }
}
