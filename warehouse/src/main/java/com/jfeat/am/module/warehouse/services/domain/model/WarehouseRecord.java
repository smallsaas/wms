package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Warehouse;

/**
 * Created by Code Generator on 2018-06-22
 */
public class WarehouseRecord extends Warehouse{
    String chargerName;


    public String getChargerName() {
        return chargerName;
    }

    public void setChargerName(String chargerName) {
        this.chargerName = chargerName;
    }
}
