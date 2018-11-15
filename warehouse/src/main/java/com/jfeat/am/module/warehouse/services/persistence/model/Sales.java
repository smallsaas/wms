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
 * @since 2018-11-14
 */
@TableName("wms_sales")
public class Sales extends Model<Sales> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 销售订单
     */
	@TableField("sales_code")
	private String salesCode;
    /**
     * 分销商ID
     */
	@TableField("trader_id")
	private Long traderId;
    /**
     * 销售订单其他支出
     */
	@TableField("sales_others_payment")
	private BigDecimal salesOthersPayment;
    /**
     * 销售订单折扣
     */
	@TableField("sales_discount")
	private Integer salesDiscount;
    /**
     * 销售订单花费
     */
	@TableField("sales_total")
	private BigDecimal salesTotal;
    /**
     * 销售订单时间
     */
	@TableField("sales_time")
	private Date salesTime;
    /**
     * 销售订单备注
     */
	@TableField("sales_note")
	private String salesNote;
    /**
     * 销售订单状态
     */
	@TableField("sales_status")
	private String salesStatus;
    /**
     * 销售订单操作人
     */
	@TableField("transaction_by")
	private String transactionBy;
    /**
     * 销售订单制单人ID
     */
	@TableField("originator_id")
	private Long originatorId;
    /**
     * 销售订单制单人
     */
	@TableField("originator_name")
	private String originatorName;
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

	public Sales setId(Long id) {
		this.id = id;
		return this;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public Sales setSalesCode(String salesCode) {
		this.salesCode = salesCode;
		return this;
	}

	public Long getTraderId() {
		return traderId;
	}

	public Sales setTraderId(Long traderId) {
		this.traderId = traderId;
		return this;
	}

	public BigDecimal getSalesOthersPayment() {
		return salesOthersPayment;
	}

	public Sales setSalesOthersPayment(BigDecimal salesOthersPayment) {
		this.salesOthersPayment = salesOthersPayment;
		return this;
	}

	public Integer getSalesDiscount() {
		return salesDiscount;
	}

	public Sales setSalesDiscount(Integer salesDiscount) {
		this.salesDiscount = salesDiscount;
		return this;
	}

	public BigDecimal getSalesTotal() {
		return salesTotal;
	}

	public Sales setSalesTotal(BigDecimal salesTotal) {
		this.salesTotal = salesTotal;
		return this;
	}

	public Date getSalesTime() {
		return salesTime;
	}

	public Sales setSalesTime(Date salesTime) {
		this.salesTime = salesTime;
		return this;
	}

	public String getSalesNote() {
		return salesNote;
	}

	public Sales setSalesNote(String salesNote) {
		this.salesNote = salesNote;
		return this;
	}

	public String getSalesStatus() {
		return salesStatus;
	}

	public Sales setSalesStatus(String salesStatus) {
		this.salesStatus = salesStatus;
		return this;
	}

	public String getTransactionBy() {
		return transactionBy;
	}

	public Sales setTransactionBy(String transactionBy) {
		this.transactionBy = transactionBy;
		return this;
	}

	public Long getOriginatorId() {
		return originatorId;
	}

	public Sales setOriginatorId(Long originatorId) {
		this.originatorId = originatorId;
		return this;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public Sales setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
		return this;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public Sales setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
		return this;
	}

	public String getField1() {
		return field1;
	}

	public Sales setField1(String field1) {
		this.field1 = field1;
		return this;
	}

	public String getField2() {
		return field2;
	}

	public Sales setField2(String field2) {
		this.field2 = field2;
		return this;
	}

	public static final String ID = "id";

	public static final String SALES_CODE = "sales_code";

	public static final String TRADER_ID = "trader_id";

	public static final String SALES_OTHERS_PAYMENT = "sales_others_payment";

	public static final String SALES_DISCOUNT = "sales_discount";

	public static final String SALES_TOTAL = "sales_total";

	public static final String SALES_TIME = "sales_time";

	public static final String SALES_NOTE = "sales_note";

	public static final String SALES_STATUS = "sales_status";

	public static final String TRANSACTION_BY = "transaction_by";

	public static final String ORIGINATOR_ID = "originator_id";

	public static final String ORIGINATOR_NAME = "originator_name";

	public static final String TRANSACTION_TIME = "transaction_time";

	public static final String FIELD1 = "field1";

	public static final String FIELD2 = "field2";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Sales{" +
			"id=" + id +
			", salesCode=" + salesCode +
			", traderId=" + traderId +
			", salesOthersPayment=" + salesOthersPayment +
			", salesDiscount=" + salesDiscount +
			", salesTotal=" + salesTotal +
			", salesTime=" + salesTime +
			", salesNote=" + salesNote +
			", salesStatus=" + salesStatus +
			", transactionBy=" + transactionBy +
			", originatorId=" + originatorId +
			", originatorName=" + originatorName +
			", transactionTime=" + transactionTime +
			", field1=" + field1 +
			", field2=" + field2 +
			"}";
	}
}
