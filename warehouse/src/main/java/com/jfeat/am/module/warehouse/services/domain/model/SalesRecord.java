package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Sales;

/**
 * Created by Code Generator on 2018-11-14
 */
public  class SalesRecord extends Sales {


    String traderName;
    String traderContactPhone;
    String traderContactName;

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getTraderContactPhone() {
        return traderContactPhone;
    }

    public void setTraderContactPhone(String traderContactPhone) {
        this.traderContactPhone = traderContactPhone;
    }

    public String getTraderContactName() {
        return traderContactName;
    }

    public void setTraderContactName(String traderContactName) {
        this.traderContactName = traderContactName;
    }
}