package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;

public class BulkUpdateOrderCount {
    String outOrderNum;

    List<UpdateOrderCount> items;

    public List<UpdateOrderCount> getItems() {
        return items;
    }

    public void setItems(List<UpdateOrderCount> items) {
        this.items = items;
    }

    public String getOutOrderNum() {
        return outOrderNum;
    }

    public void setOutOrderNum(String outOrderNum) {
        this.outOrderNum = outOrderNum;
    }
}

