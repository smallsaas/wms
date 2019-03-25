package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;

public class BulkUpdateOrderCount {
    Integer dealSuccess;
    String orderId;

    List<UpdateOrderCount> items;

    public List<UpdateOrderCount> getItems() {
        return items;
    }

    public void setItems(List<UpdateOrderCount> items) {
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getDealSuccess() {
        return dealSuccess;
    }

    public void setDealSuccess(Integer dealSuccess) {
        this.dealSuccess = dealSuccess;
    }
}

