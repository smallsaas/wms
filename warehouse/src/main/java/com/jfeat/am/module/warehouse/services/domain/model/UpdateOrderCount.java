package com.jfeat.am.module.warehouse.services.domain.model;

/**
 * Created by leuo on 2019/1/17.
 */
public class UpdateOrderCount {
    Long skuId;
    Long warehouseId;
    Integer orderCount;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
}
