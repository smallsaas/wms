package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;

public class SalesDetails extends SalesRecord{

    List<StorageOutRecord> storageOutItemRecords;

    List<StorageOutItemRecord>  itemRecords;


    public List<StorageOutRecord> getStorageOutItemRecords() {
        return storageOutItemRecords;
    }

    public void setStorageOutItemRecords(List<StorageOutRecord> storageOutItemRecords) {
        this.storageOutItemRecords = storageOutItemRecords;
    }

    public List<StorageOutItemRecord> getItemRecords() {
        return itemRecords;
    }

    public void setItemRecords(List<StorageOutItemRecord> itemRecords) {
        this.itemRecords = itemRecords;
    }
}
