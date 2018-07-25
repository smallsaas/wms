package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-21
 */
@TableName("wms_storage_in_item")
public class StorageInItem extends Model<StorageInItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 入库ID
     */
    @TableField("storage_in_id")
    private Long storageInId;
    /**
     * SKUID
     */
    @TableField("sku_id")
    private Long skuId;
    /**
     * 出入库价格
     */
    @TableField("transaction_sku_price")
    private BigDecimal transactionSkuPrice;
    /**
     * 操作数量
     */
    @TableField("transaction_quantities")
    private Integer transactionQuantities;
    /**
     * 操作时间
     */
    @TableField("transaction_time")
    private Date transactionTime;
    /**
     * 出入库操作仓库ID
     */
    @TableField("warehouse_id")
    private Long warehouseId;

    /**
     * 储位
     */
    @TableField("slot_id")
    private Long slotId;


    public Long getId() {
        return id;
    }

    public StorageInItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getStorageInId() {
        return storageInId;
    }

    public StorageInItem setStorageInId(Long storageInId) {
        this.storageInId = storageInId;
        return this;
    }

    public Long getSkuId() {
        return skuId;
    }

    public StorageInItem setSkuId(Long skuId) {
        this.skuId = skuId;
        return this;
    }

    public BigDecimal getTransactionSkuPrice() {
        return transactionSkuPrice;
    }

    public StorageInItem setTransactionSkuPrice(BigDecimal transactionSkuPrice) {
        this.transactionSkuPrice = transactionSkuPrice;
        return this;
    }

    public Integer getTransactionQuantities() {
        return transactionQuantities;
    }

    public StorageInItem setTransactionQuantities(Integer transactionQuantities) {
        this.transactionQuantities = transactionQuantities;
        return this;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public StorageInItem setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
        return this;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public StorageInItem setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public static final String ID = "id";

    public static final String STORAGE_IN_ID = "storage_in_id";

    public static final String SKU_ID = "sku_id";

    public static final String TRANSACTION_SKU_PRICE = "transaction_sku_price";

    public static final String TRANSACTION_QUANTITIES = "transaction_quantities";

    public static final String TRANSACTION_TIME = "transaction_time";

    public static final String WAREHOUSE_ID = "warehouse_id";

    public static final String SLOT_ID = "slot_id";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StorageInItem{" +
                "id=" + id +
                ", storageInId=" + storageInId +
                ", skuId=" + skuId +
                ", transactionSkuPrice=" + transactionSkuPrice +
                ", transactionQuantities=" + transactionQuantities +
                ", transactionTime=" + transactionTime +
                ", warehouseId=" + warehouseId +
                ", slotId=" + slotId +
                "}";
    }
}