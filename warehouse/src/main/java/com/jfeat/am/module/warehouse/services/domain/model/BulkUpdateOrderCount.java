package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;

public class BulkUpdateOrderCount {

    List<UpdateOrderCount> items;

    public List<UpdateOrderCount> getItems() {
        return items;
    }

    public void setItems(List<UpdateOrderCount> items) {
        this.items = items;
    }
}

