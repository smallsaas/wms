package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;

import java.util.List;

public class SalesDetails extends SalesRecord{

    List<StorageOutRecord> storageOutItemRecords;

    List<StorageOutItemRecord>  itemRecords;

    List<StorageOutItem> outItems;


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

    public List<StorageOutItem> getOutItems() {
        return outItems;
    }

    public void setOutItems(List<StorageOutItem> outItems) {
        this.outItems = outItems;
    }
}
