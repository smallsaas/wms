package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-08-29
 */
@TableName("wms_storage_out")
public class StorageOut extends Model<StorageOut> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用于隔离的组织id, 由crud-plus维护
     */
    @TableField("org_id")
    private  Long orgId;
    /**
     * 用于隔离的组织标识, 参考 docker而定
     */
    @TableField("org_tag")
    private String orgTag;
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
     * out time
     */
    @TableField("storage_out_time")
    private Date storageOutTime;
    /**
     * 批次
     */
    @TableField("batch_no")
    private String batchNo;
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
     * 制单人ID
     */
    @TableField("originator_id")
    private Long originatorId;
    /**
     * 制单人
     */
    @TableField("originator_name")
    private String originatorName;
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
     * 保留字段
     */
    private String field1;
    /**
     * 保留字段
     */
    private String field2;

    /**
     * 订单号信息
     */
    @TableField("out_order_num")
    private String outOrderNum;


    /**
     * 订单号信息
     */
    @TableField("distributor_customer")
    private String distributorCustomer;

    /**
     * 储位
     */
    @TableField("sales_id")
    private Long salesId;

    public Long getId() {
        return id;
    }

    public StorageOut setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrgId() {
        return orgId;
    }

    public StorageOut setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getOrgTag() {
        return orgTag;
    }

    public StorageOut setOrgTag(String orgTag) {
        this.orgTag = orgTag;
        return this;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public StorageOut setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public StorageOut setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public StorageOut setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
        return this;
    }

    public Date getStorageOutTime() {
        return storageOutTime;
    }

    public StorageOut setStorageOutTime(Date storageOutTime) {
        this.storageOutTime = storageOutTime;
        return this;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public StorageOut setBatchNo(String batchNo) {
        this.batchNo = batchNo;
        return this;
    }

    public String getNote() {
        return note;
    }

    public StorageOut setNote(String note) {
        this.note = note;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public StorageOut setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public StorageOut setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
        return this;
    }

    public Long getOriginatorId() {
        return originatorId;
    }

    public StorageOut setOriginatorId(Long originatorId) {
        this.originatorId = originatorId;
        return this;
    }

    public String getOriginatorName() {
        return originatorName;
    }

    public StorageOut setOriginatorName(String originatorName) {
        this.originatorName = originatorName;
        return this;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public StorageOut setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public Long getSlotId() {
        return slotId;
    }

    public StorageOut setSlotId(Long slotId) {
        this.slotId = slotId;
        return this;
    }

    public String getField1() {
        return field1;
    }

    public StorageOut setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public String getField2() {
        return field2;
    }

    public StorageOut setField2(String field2) {
        this.field2 = field2;
        return this;
    }

    public String getOutOrderNum() {
        return outOrderNum;
    }

    public void setOutOrderNum(String outOrderNum) {
        this.outOrderNum = outOrderNum;
    }

    public String getDistributorCustomer() {
        return distributorCustomer;
    }

    public void setDistributorCustomer(String distributorCustomer) {
        this.distributorCustomer = distributorCustomer;
    }

    public Long getSalesId() {
        return salesId;
    }

    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    public static final String ID = "id";

    public static final String TRANSACTION_CODE = "transaction_code";

    public static final String TRANSACTION_TYPE = "transaction_type";

    public static final String TRANSACTION_TIME = "transaction_time";

    public static final String STORAGE_OUT_TIME = "storage_out_time";

    public static final String BATCH_NO = "batch_no";

    public static final String NOTE = "note";

    public static final String STATUS = "status";

    public static final String TRANSACTION_BY = "transaction_by";

    public static final String ORIGINATOR_ID = "originator_id";

    public static final String ORIGINATOR_NAME = "originator_name";

    public static final String WAREHOUSE_ID = "warehouse_id";

    public static final String SLOT_ID = "slot_id";

    public static final String FIELD1 = "field1";

    public static final String FIELD2 = "field2";

    public static final String OUT_ORDER_NUM = "out_order_num";
    public static final String DISTRIBUTOR_CUSTOMER = "distributor_customer";

    public static final String ORG_ID = "org_id";

    public static final String ORG_TAG = "org_tag";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StorageOut{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", orgTag='" + orgTag + '\'' +
                ", transactionCode='" + transactionCode + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionTime=" + transactionTime +
                ", storageOutTime=" + storageOutTime +
                ", batchNo='" + batchNo + '\'' +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", transactionBy='" + transactionBy + '\'' +
                ", originatorId=" + originatorId +
                ", originatorName='" + originatorName + '\'' +
                ", warehouseId=" + warehouseId +
                ", slotId=" + slotId +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", outOrderNum='" + outOrderNum + '\'' +
                ", distributorCustomer='" + distributorCustomer + '\'' +
                ", salesId=" + salesId +
                '}';
    }
}
