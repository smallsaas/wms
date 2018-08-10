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
 * @since 2018-06-20
 */
@TableName("wms_storage_out_item")
public class StorageOutItem extends Model<StorageOutItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 出库ID
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
     * 操作时间
     */
    @TableField("transaction_time")
    private Date transactionTime;

    /**
     * 关联的操作编号
     */
    @TableField("relation_code")
    private String relationCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStorageOutId() {
        return storageOutId;
    }

    public void setStorageOutId(Long storageOutId) {
        this.storageOutId = storageOutId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getTransactionSkuPrice() {
        return transactionSkuPrice;
    }

    public void setTransactionSkuPrice(BigDecimal transactionSkuPrice) {
        this.transactionSkuPrice = transactionSkuPrice;
    }

    public Integer getTransactionQuantities() {
        return transactionQuantities;
    }

    public void setTransactionQuantities(Integer transactionQuantities) {
        this.transactionQuantities = transactionQuantities;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public static final String ID = "id";

    public static final String STORAGE_OUT_ID = "storage_out_id";

    public static final String SKU_ID = "sku_id";

    public static final String TRANSACTION_SKU_PRICE = "transaction_sku_price";

    public static final String TRANSACTION_QUANTITIES = "transaction_quantities";

    public static final String TRANSACTION_TIME = "transaction_time";

    public static final String RELATION_CODE = "relation_code";


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
                ", transactionTime=" + transactionTime +
                ", relationCode=" + relationCode +
                "}";
    }
}
