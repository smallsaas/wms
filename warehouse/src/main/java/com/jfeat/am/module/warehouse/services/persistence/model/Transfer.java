package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

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
 * @since 2018-06-22
 */
@TableName("wms_transfer")
public class Transfer extends Model<Transfer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作编号
     */
    @TableField("transaction_code")
    private String transactionCode;
    /**
     * 调出仓库ID
     */
    @TableField("from_warehouse_id")
    private Long fromWarehouseId;
    /**
     * 调入仓库ID
     */
    @TableField("to_warehouse_id")
    private Long toWarehouseId;
    /**
     * 操作时间
     */
    @TableField("transaction_time")
    private Date transactionTime;
    /**
     * 完成时间
     */
    @TableField("finish_time")
    private Date finishTime;
    /**
     * 入库ID
     */
    @TableField("storage_in_id")
    private Long storageInId;
    /**
     * 出库ID
     */
    @TableField("storage_out_id")
    private Long storageOutId;
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
     * 保留字段
     */
    private String field1;
    /**
     * 保留字段
     */
    private String field2;


    public Long getId() {
        return id;
    }

    public Transfer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public Transfer setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
        return this;
    }

    public Long getFromWarehouseId() {
        return fromWarehouseId;
    }

    public Transfer setFromWarehouseId(Long fromWarehouseId) {
        this.fromWarehouseId = fromWarehouseId;
        return this;
    }

    public Long getToWarehouseId() {
        return toWarehouseId;
    }

    public Transfer setToWarehouseId(Long toWarehouseId) {
        this.toWarehouseId = toWarehouseId;
        return this;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public Transfer setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
        return this;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Long getStorageInId() {
        return storageInId;
    }

    public Transfer setStorageInId(Long storageInId) {
        this.storageInId = storageInId;
        return this;
    }

    public Long getStorageOutId() {
        return storageOutId;
    }

    public Transfer setStorageOutId(Long storageOutId) {
        this.storageOutId = storageOutId;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Transfer setNote(String note) {
        this.note = note;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Transfer setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }

    public Long getOriginatorId() {
        return originatorId;
    }

    public Transfer setOriginatorId(Long originatorId) {
        this.originatorId = originatorId;
        return this;
    }

    public String getField1() {
        return field1;
    }

    public Transfer setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public String getField2() {
        return field2;
    }

    public Transfer setField2(String field2) {
        this.field2 = field2;
        return this;
    }

    public static final String ID = "id";

    public static final String TRANSACTION_CODE = "transaction_code";

    public static final String FROM_WAREHOUSE_ID = "from_warehouse_id";

    public static final String TO_WAREHOUSE_ID = "to_warehouse_id";

    public static final String TRANSACTION_TIME = "transaction_time";

    public static final String FINISH_TIME = "finish_time";

    public static final String STORAGE_IN_ID = "storage_in_id";

    public static final String STORAGE_OUT_ID = "storage_out_id";

    public static final String NOTE = "note";

    public static final String STATUS = "status";

    public static final String TRANSACTION_BY = "transaction_by";

    public static final String ORIGINATOR_ID = "originator_id";

    public static final String FIELD1 = "field1";

    public static final String FIELD2 = "field2";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", transactionCode=" + transactionCode +
                ", fromWarehouseId=" + fromWarehouseId +
                ", toWarehouseId=" + toWarehouseId +
                ", transactionTime=" + transactionTime +
                ", finishTime=" + finishTime +
                ", storageInId=" + storageInId +
                ", storageOutId=" + storageOutId +
                ", note=" + note +
                ", status=" + status +
                ", transactionBy=" + transactionBy +
                ", originatorId=" + originatorId +
                ", field1=" + field1 +
                ", field2=" + field2 +
                "}";
    }
}
