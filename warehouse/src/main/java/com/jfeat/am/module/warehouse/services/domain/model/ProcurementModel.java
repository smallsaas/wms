package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Procurement;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public class ProcurementModel extends Procurement{

    List<StorageInItem> items;

    String originatorName;
    String operatorName;

    Integer SectionStorageIn;

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

    public List<StorageInItem> getItems() {
        return items;
    }

    public void setItems(List<StorageInItem> items) {
        this.items = items;
    }

    public Integer getSectionStorageIn() {
        return SectionStorageIn;
    }

    public void setSectionStorageIn(Integer sectionStorageIn) {
        SectionStorageIn = sectionStorageIn;
    }
}
