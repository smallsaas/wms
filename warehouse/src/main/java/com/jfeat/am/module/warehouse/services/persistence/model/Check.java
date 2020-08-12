package com.jfeat.am.module.warehouse.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("wms_check")
public class Check extends Model<Check> {

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
     * 盘点编号
     */
	@TableField("check_code")
	private String checkCode;
    /**
     * 盘点时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("check_time")
	private Date checkTime;
    /**
     * 操作时间--finish
     */
	@TableField("finish_time")
	private Date finishTime;
    /**
     * 操作时间--begin
     */
	@TableField("begin_time")
	private Date beginTime;
    /**
     * 盘点仓库ID
     */
	@TableField("warehouse_id")
	private Long warehouseId;
    /**
     * 盈亏(缺失值)
     */
	@TableField("profit_lost")
	private Integer profitLost;
    /**
     * 盘点备注
     */
	@TableField("check_note")
	private String checkNote;
    /**
     * 经手人
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
     * check status
     */
	private String status;
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

	public Check setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Check setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public Check setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public Check setCheckCode(String checkCode) {
		this.checkCode = checkCode;
		return this;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public Check setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
		return this;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public Check setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
		return this;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Check setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
		return this;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public Check setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
		return this;
	}

	public Integer getProfitLost() {
		return profitLost;
	}

	public Check setProfitLost(Integer profitLost) {
		this.profitLost = profitLost;
		return this;
	}

	public String getCheckNote() {
		return checkNote;
	}

	public Check setCheckNote(String checkNote) {
		this.checkNote = checkNote;
		return this;
	}

	public String getTransactionBy() {
		return transactionBy;
	}

	public Check setTransactionBy(String transactionBy) {
		this.transactionBy = transactionBy;
		return this;
	}

	public Long getOriginatorId() {
		return originatorId;
	}

	public Check setOriginatorId(Long originatorId) {
		this.originatorId = originatorId;
		return this;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public Check setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Check setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getField1() {
		return field1;
	}

	public Check setField1(String field1) {
		this.field1 = field1;
		return this;
	}

	public String getField2() {
		return field2;
	}

	public Check setField2(String field2) {
		this.field2 = field2;
		return this;
	}

	public static final String ID = "id";

	public static final String CHECK_CODE = "check_code";

	public static final String CHECK_TIME = "check_time";

	public static final String FINISH_TIME = "finish_time";

	public static final String BEGIN_TIME = "begin_time";

	public static final String WAREHOUSE_ID = "warehouse_id";

	public static final String PROFIT_LOST = "profit_lost";

	public static final String CHECK_NOTE = "check_note";

	public static final String TRANSACTION_BY = "transaction_by";

	public static final String ORIGINATOR_ID = "originator_id";

	public static final String ORIGINATOR_NAME = "originator_name";

	public static final String STATUS = "status";

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
		return "Check{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", checkCode='" + checkCode + '\'' +
				", checkTime=" + checkTime +
				", finishTime=" + finishTime +
				", beginTime=" + beginTime +
				", warehouseId=" + warehouseId +
				", profitLost=" + profitLost +
				", checkNote='" + checkNote + '\'' +
				", transactionBy='" + transactionBy + '\'' +
				", originatorId=" + originatorId +
				", originatorName='" + originatorName + '\'' +
				", status='" + status + '\'' +
				", field1='" + field1 + '\'' +
				", field2='" + field2 + '\'' +
				'}';
	}
}
