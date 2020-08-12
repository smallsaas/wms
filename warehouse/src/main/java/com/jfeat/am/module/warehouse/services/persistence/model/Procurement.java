package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-08-29
 */
@TableName("wms_procurement")
public class Procurement extends Model<Procurement> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
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
     * 采购表编号
     */
	@TableField("procurement_code")
	private String procurementCode;
    /**
     * 供应商ID
     */
	@TableField("supplier_id")
	private Long supplierId;
    /**
     * 采购其他支出
     */
	@TableField("procurement_others_payment")
	private BigDecimal procurementOthersPayment;
    /**
     * 采购折扣
     */
	@TableField("procurement_discount")
	private Integer procurementDiscount;
    /**
     * 总花费
     */
	@TableField("procurement_total")
	private BigDecimal procurementTotal;
    /**
     * 采购时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("procurement_time")
	private Date procurementTime;
    /**
     * 采购备注
     */
	@TableField("procurement_note")
	private String procurementNote;
    /**
     * 状态
     */
	@TableField("procure_status")
	private String procureStatus;
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
     * 采购员
     */
	private String buyer;
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

	public Procurement setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Procurement setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public Procurement setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getProcurementCode() {
		return procurementCode;
	}

	public Procurement setProcurementCode(String procurementCode) {
		this.procurementCode = procurementCode;
		return this;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public Procurement setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
		return this;
	}

	public BigDecimal getProcurementOthersPayment() {
		return procurementOthersPayment;
	}

	public Procurement setProcurementOthersPayment(BigDecimal procurementOthersPayment) {
		this.procurementOthersPayment = procurementOthersPayment;
		return this;
	}

	public Integer getProcurementDiscount() {
		return procurementDiscount;
	}

	public Procurement setProcurementDiscount(Integer procurementDiscount) {
		this.procurementDiscount = procurementDiscount;
		return this;
	}

	public BigDecimal getProcurementTotal() {
		return procurementTotal;
	}

	public Procurement setProcurementTotal(BigDecimal procurementTotal) {
		this.procurementTotal = procurementTotal;
		return this;
	}

	public Date getProcurementTime() {
		return procurementTime;
	}

	public Procurement setProcurementTime(Date procurementTime) {
		this.procurementTime = procurementTime;
		return this;
	}

	public String getProcurementNote() {
		return procurementNote;
	}

	public Procurement setProcurementNote(String procurementNote) {
		this.procurementNote = procurementNote;
		return this;
	}

	public String getProcureStatus() {
		return procureStatus;
	}

	public Procurement setProcureStatus(String procureStatus) {
		this.procureStatus = procureStatus;
		return this;
	}

	public String getTransactionBy() {
		return transactionBy;
	}

	public Procurement setTransactionBy(String transactionBy) {
		this.transactionBy = transactionBy;
		return this;
	}

	public Long getOriginatorId() {
		return originatorId;
	}

	public Procurement setOriginatorId(Long originatorId) {
		this.originatorId = originatorId;
		return this;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public Procurement setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
		return this;
	}

	public String getBuyer() {
		return buyer;
	}

	public Procurement setBuyer(String buyer) {
		this.buyer = buyer;
		return this;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public Procurement setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
		return this;
	}

	public String getField1() {
		return field1;
	}

	public Procurement setField1(String field1) {
		this.field1 = field1;
		return this;
	}

	public String getField2() {
		return field2;
	}

	public Procurement setField2(String field2) {
		this.field2 = field2;
		return this;
	}

	public static final String ID = "id";

	public static final String PROCUREMENT_CODE = "procurement_code";

	public static final String SUPPLIER_ID = "supplier_id";

	public static final String PROCUREMENT_OTHERS_PAYMENT = "procurement_others_payment";

	public static final String PROCUREMENT_DISCOUNT = "procurement_discount";

	public static final String PROCUREMENT_TOTAL = "procurement_total";

	public static final String PROCUREMENT_TIME = "procurement_time";

	public static final String PROCUREMENT_NOTE = "procurement_note";

	public static final String PROCURE_STATUS = "procure_status";

	public static final String TRANSACTION_BY = "transaction_by";

	public static final String ORIGINATOR_ID = "originator_id";

	public static final String ORIGINATOR_NAME = "originator_name";

	public static final String BUYER = "buyer";

	public static final String TRANSACTION_TIME = "transaction_time";

	public static final String FIELD1 = "field1";

	public static final String FIELD2 = "field2";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Procurement{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", procurementCode='" + procurementCode + '\'' +
				", supplierId=" + supplierId +
				", procurementOthersPayment=" + procurementOthersPayment +
				", procurementDiscount=" + procurementDiscount +
				", procurementTotal=" + procurementTotal +
				", procurementTime=" + procurementTime +
				", procurementNote='" + procurementNote + '\'' +
				", procureStatus='" + procureStatus + '\'' +
				", transactionBy='" + transactionBy + '\'' +
				", originatorId=" + originatorId +
				", originatorName='" + originatorName + '\'' +
				", buyer='" + buyer + '\'' +
				", transactionTime=" + transactionTime +
				", field1='" + field1 + '\'' +
				", field2='" + field2 + '\'' +
				'}';
	}
}
