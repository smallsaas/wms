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
 * @since 2018-08-29
 */
@TableName("wms_refund")
public class Refund extends Model<Refund> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 退货编号
     */
	@TableField("product_refund_code")
	private String productRefundCode;
    /**
     * 采购订单ID
     */
	@TableField("product_procurement_id")
	private Long productProcurementId;
    /**
     * 入库ID
     */
	@TableField("storage_out_id")
	private Long storageOutId;
    /**
     * 退货仓库
     */
	@TableField("product_refund_warehouse_id")
	private Long productRefundWarehouseId;
    /**
     * 可退数量
     */
	@TableField("product_refund_quantities")
	private Integer productRefundQuantities;
    /**
     * 退货时间
     */
	@TableField("product_refund_time")
	private Date productRefundTime;
    /**
     * 状态
     */
	@TableField("product_refund_status")
	private String productRefundStatus;
    /**
     * 备注
     */
	@TableField("product_refund_note")
	private String productRefundNote;
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
     * 操作人
     */
	@TableField("transaction_by")
	private String transactionBy;
    /**
     * 操作时间
     */
	@TableField("transaction_time")
	private Date transactionTime;
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

	public Refund setId(Long id) {
		this.id = id;
		return this;
	}

	public String getProductRefundCode() {
		return productRefundCode;
	}

	public Refund setProductRefundCode(String productRefundCode) {
		this.productRefundCode = productRefundCode;
		return this;
	}

	public Long getProductProcurementId() {
		return productProcurementId;
	}

	public Refund setProductProcurementId(Long productProcurementId) {
		this.productProcurementId = productProcurementId;
		return this;
	}

	public Long getStorageOutId() {
		return storageOutId;
	}

	public Refund setStorageOutId(Long storageOutId) {
		this.storageOutId = storageOutId;
		return this;
	}

	public Long getProductRefundWarehouseId() {
		return productRefundWarehouseId;
	}

	public Refund setProductRefundWarehouseId(Long productRefundWarehouseId) {
		this.productRefundWarehouseId = productRefundWarehouseId;
		return this;
	}

	public Integer getProductRefundQuantities() {
		return productRefundQuantities;
	}

	public Refund setProductRefundQuantities(Integer productRefundQuantities) {
		this.productRefundQuantities = productRefundQuantities;
		return this;
	}

	public Date getProductRefundTime() {
		return productRefundTime;
	}

	public Refund setProductRefundTime(Date productRefundTime) {
		this.productRefundTime = productRefundTime;
		return this;
	}

	public String getProductRefundStatus() {
		return productRefundStatus;
	}

	public Refund setProductRefundStatus(String productRefundStatus) {
		this.productRefundStatus = productRefundStatus;
		return this;
	}

	public String getProductRefundNote() {
		return productRefundNote;
	}

	public Refund setProductRefundNote(String productRefundNote) {
		this.productRefundNote = productRefundNote;
		return this;
	}

	public Long getOriginatorId() {
		return originatorId;
	}

	public Refund setOriginatorId(Long originatorId) {
		this.originatorId = originatorId;
		return this;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public Refund setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
		return this;
	}

	public String getTransactionBy() {
		return transactionBy;
	}

	public Refund setTransactionBy(String transactionBy) {
		this.transactionBy = transactionBy;
		return this;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public Refund setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
		return this;
	}

	public String getField1() {
		return field1;
	}

	public Refund setField1(String field1) {
		this.field1 = field1;
		return this;
	}

	public String getField2() {
		return field2;
	}

	public Refund setField2(String field2) {
		this.field2 = field2;
		return this;
	}

	public static final String ID = "id";

	public static final String PRODUCT_REFUND_CODE = "product_refund_code";

	public static final String PRODUCT_PROCUREMENT_ID = "product_procurement_id";

	public static final String STORAGE_OUT_ID = "storage_out_id";

	public static final String PRODUCT_REFUND_WAREHOUSE_ID = "product_refund_warehouse_id";

	public static final String PRODUCT_REFUND_QUANTITIES = "product_refund_quantities";

	public static final String PRODUCT_REFUND_TIME = "product_refund_time";

	public static final String PRODUCT_REFUND_STATUS = "product_refund_status";

	public static final String PRODUCT_REFUND_NOTE = "product_refund_note";

	public static final String ORIGINATOR_ID = "originator_id";

	public static final String ORIGINATOR_NAME = "originator_name";

	public static final String TRANSACTION_BY = "transaction_by";

	public static final String TRANSACTION_TIME = "transaction_time";

	public static final String FIELD1 = "field1";

	public static final String FIELD2 = "field2";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Refund{" +
			"id=" + id +
			", productRefundCode=" + productRefundCode +
			", productProcurementId=" + productProcurementId +
			", storageOutId=" + storageOutId +
			", productRefundWarehouseId=" + productRefundWarehouseId +
			", productRefundQuantities=" + productRefundQuantities +
			", productRefundTime=" + productRefundTime +
			", productRefundStatus=" + productRefundStatus +
			", productRefundNote=" + productRefundNote +
			", originatorId=" + originatorId +
			", originatorName=" + originatorName +
			", transactionBy=" + transactionBy +
			", transactionTime=" + transactionTime +
			", field1=" + field1 +
			", field2=" + field2 +
			"}";
	}
}
