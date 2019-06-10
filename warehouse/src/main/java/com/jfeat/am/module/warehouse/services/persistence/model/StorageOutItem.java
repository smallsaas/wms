package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-08-29
 */
@TableName("wms_storage_out_item")
public class StorageOutItem extends Model<StorageOutItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 入库ID
     */
    @TableField("storage_out_id")
    private Long storageOutId;
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
     * 操作后数量
     */
    @TableField("after_transaction_quantities")
    private Integer afterTransactionQuantities;
    /**
     * 操作时间
     */
    @TableField("transaction_time")
    private Date transactionTime;
    /**
     * 关联的操作编号
     */
    @TableField("relation_code")
    private String relationCode;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 需求数量
     */
    @TableField("demand_quantities")
    private Integer demandQuantities;

    /**
     * 操作前数量
     */
    @TableField("before_transaction_quantities")
    private Integer beforeTransactionQuantities;


    public Long getId() {
        return id;
    }

    public StorageOutItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getStorageOutId() {
        return storageOutId;
    }

    public StorageOutItem setStorageOutId(Long storageOutId) {
        this.storageOutId = storageOutId;
        return this;
    }

    public Long getSkuId() {
        return skuId;
    }

    public StorageOutItem setSkuId(Long skuId) {
        this.skuId = skuId;
        return this;
    }

    public BigDecimal getTransactionSkuPrice() {
        return transactionSkuPrice;
    }

    public StorageOutItem setTransactionSkuPrice(BigDecimal transactionSkuPrice) {
        this.transactionSkuPrice = transactionSkuPrice;
        return this;
    }

    public Integer getTransactionQuantities() {
        return transactionQuantities;
    }

    public StorageOutItem setTransactionQuantities(Integer transactionQuantities) {
        this.transactionQuantities = transactionQuantities;
        return this;
    }

    public Integer getAfterTransactionQuantities() {
        return afterTransactionQuantities;
    }

    public StorageOutItem setAfterTransactionQuantities(Integer afterTransactionQuantities) {
        this.afterTransactionQuantities = afterTransactionQuantities;
        return this;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public StorageOutItem setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
        return this;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public StorageOutItem setRelationCode(String relationCode) {
        this.relationCode = relationCode;
        return this;
    }

    public Integer getBeforeTransactionQuantities() {
        return beforeTransactionQuantities;
    }

    public void setBeforeTransactionQuantities(Integer beforeTransactionQuantities) {
        this.beforeTransactionQuantities = beforeTransactionQuantities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDemandQuantities() {
        return demandQuantities;
    }

    public void setDemandQuantities(Integer demandQuantities) {
        this.demandQuantities = demandQuantities;
    }

    public static final String ID = "id";

    public static final String STORAGE_OUT_ID = "storage_out_id";

    public static final String SKU_ID = "sku_id";

    public static final String TRANSACTION_SKU_PRICE = "transaction_sku_price";

    public static final String TRANSACTION_QUANTITIES = "transaction_quantities";

    public static final String AFTER_TRANSACTION_QUANTITIES = "after_transaction_quantities";

    public static final String TRANSACTION_TIME = "transaction_time";

    public static final String RELATION_CODE = "relation_code";

    public static final String TYPE = "type";

    public static final String BEFORE_TRANSACTION_QUANTITIES = "before_transaction_quantities";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StorageOutItem{" +
                "id=" + id +
                ", storageOutId=" + storageOutId +
                ", skuId=" + skuId +
                ", transactionSkuPrice=" + transactionSkuPrice +
                ", transactionQuantities=" + transactionQuantities +
                ", afterTransactionQuantities=" + afterTransactionQuantities +
                ", beforeTransactionQuantities=" + beforeTransactionQuantities +

                ", transactionTime=" + transactionTime +
                ", relationCode=" + relationCode +
                ", type=" + type +
                "}";
    }
}
