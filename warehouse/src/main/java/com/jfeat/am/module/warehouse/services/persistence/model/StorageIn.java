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
 * <p>
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-21
 */
@TableName("wms_storage_in")
public class StorageIn extends Model<StorageIn> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作编号
     */
    @TableField("transaction_code")
    private String transactionCode;
    /**
     * 操作类型
     */
    @TableField("transaction_type")
    private String transactionType;
    /**
     * 操作时间
     */
    @TableField("transaction_time")
    private Date transactionTime;
    /**
     * 批次
     */
    @TableField("batch_no")
    private String batchNo;
    /**
     * 仓库
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 储位
     */
    @TableField("slot_id")
    private Long slotId;
    /**
     * 备注
     */
    private String note;
    /**
     * 状态
     */
    private String status;
    /**
     * 操作人
     */
    @TableField("transaction_by")
    private String transactionBy;
    /**
     * 制单人
     */
    @TableField("originator_id")
    private Long originatorId;
    /**
     * 入库成本调整
     */
    @TableField("readjust_cost_price")
    private BigDecimal readjustCostPrice;
    /**
     * 保留字段
     */
    private String field1;
    /**
     * 保留字段
     */
    private String field2;

    /**
     * 仓库
     */
    @TableField("procurement_id")
    private Long procurementId;


    public Long getId() {
        return id;
    }

    public StorageIn setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public StorageIn setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public StorageIn setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public StorageIn setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
        return this;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public StorageIn setBatchNo(String batchNo) {
        this.batchNo = batchNo;
        return this;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public StorageIn setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public Long getSlotId() {
        return slotId;
    }

    public StorageIn setSlotId(Long slotId) {
        this.slotId = slotId;
        return this;
    }

    public String getNote() {
        return note;
    }

    public StorageIn setNote(String note) {
        this.note = note;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public StorageIn setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public StorageIn setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
        return this;
    }

    public Long getOriginatorId() {
        return originatorId;
    }

    public StorageIn setOriginatorId(Long originatorId) {
        this.originatorId = originatorId;
        return this;
    }

    public BigDecimal getReadjustCostPrice() {
        return readjustCostPrice;
    }

    public StorageIn setReadjustCostPrice(BigDecimal readjustCostPrice) {
        this.readjustCostPrice = readjustCostPrice;
        return this;
    }

    public String getField1() {
        return field1;
    }

    public StorageIn setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public String getField2() {
        return field2;
    }

    public StorageIn setField2(String field2) {
        this.field2 = field2;
        return this;
    }

    public Long getProcurementId() {
        return procurementId;
    }

    public void setProcurementId(Long procurementId) {
        this.procurementId = procurementId;
    }

    public static final String ID = "id";

    public static final String TRANSACTION_CODE = "transaction_code";

    public static final String TRANSACTION_TYPE = "transaction_type";

    public static final String TRANSACTION_TIME = "transaction_time";

    public static final String BATCH_NO = "batch_no";

    public static final String WAREHOUSE_ID = "warehouse_id";

    public static final String SLOT_ID = "slot_id";

    public static final String NOTE = "note";

    public static final String STATUS = "status";

    public static final String TRANSACTION_BY = "transaction_by";

    public static final String ORIGINATOR_ID = "originator_id";

    public static final String READJUST_COST_PRICE = "readjust_cost_price";

    public static final String FIELD1 = "field1";

    public static final String FIELD2 = "field2";

    public static final String PROCUREMENT_ID = "procurement_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StorageIn{" +
                "id=" + id +
                ", transactionCode=" + transactionCode +
                ", transactionType=" + transactionType +
                ", transactionTime=" + transactionTime +
                ", batchNo=" + batchNo +
                ", warehouseId=" + warehouseId +
                ", slotId=" + slotId +
                ", note=" + note +
                ", status=" + status +
                ", transactionBy=" + transactionBy +
                ", originatorId=" + originatorId +
                ", readjustCostPrice=" + readjustCostPrice +
                ", field1=" + field1 +
                ", field2=" + field2 +
                ", procurementId=" + procurementId +
                "}";
    }
}
