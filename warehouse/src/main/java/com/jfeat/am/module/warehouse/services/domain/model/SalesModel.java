package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Sales;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;

import java.util.List;

/**
 * Created by Code Generator on 2018-11-14
 */
public class SalesModel extends Sales {

    List<StorageOutItem> outItems;


    public List<StorageOutItem> getOutItems() {
        return outItems;
    }

    public void setOutItems(List<StorageOutItem> outItems) {
        this.outItems = outItems;
    }
}
