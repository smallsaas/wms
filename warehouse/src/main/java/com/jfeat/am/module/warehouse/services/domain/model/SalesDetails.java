package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;

public class SalesDetails extends SalesRecord{

    List<StorageOutRecord> storageOutItemRecords;

    List<StorageOutItemRecord> outItems;



    public List<StorageOutRecord> getStorageOutItemRecords() {
        return storageOutItemRecords;
    }

    public void setStorageOutItemRecords(List<StorageOutRecord> storageOutItemRecords) {
        this.storageOutItemRecords = storageOutItemRecords;
    }

    public List<StorageOutItemRecord> getOutItems() {
        return outItems;
    }

    public void setOutItems(List<StorageOutItemRecord> outItems) {
        this.outItems = outItems;
    }

}
