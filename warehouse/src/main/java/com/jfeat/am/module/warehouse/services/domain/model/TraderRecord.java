package com.jfeat.am.module.warehouse.services.domain.model;


import com.jfeat.am.module.warehouse.services.persistence.model.Trader;

/**
 * Created by Code Generator on 2018-11-14
 */
public  class TraderRecord extends Trader {

    String deliveredAddress;

    public String getDeliveredAddress() {
        return deliveredAddress;
    }

    public void setDeliveredAddress(String deliveredAddress) {
        this.deliveredAddress = deliveredAddress;
    }
}