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
 * @since 2018-06-23
 */
@TableName("wms_check")
public class Check extends Model<Check> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 盘点编号
     */
	@TableField("check_code")
	private String checkCode;
    /**
     * 盘点时间
     */
	@TableField("check_time")
	private Date checkTime;
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
	@TableField("check_by")
	private Long checkBy;
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

	public Check setId(Long id) {
		this.id = id;
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

	public Long getCheckBy() {
		return checkBy;
	}

	public Check setCheckBy(Long checkBy) {
		this.checkBy = checkBy;
		return this;
	}

	public Long getOriginatorId() {
		return originatorId;
	}

	public Check setOriginatorId(Long originatorId) {
		this.originatorId = originatorId;
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

	public static final String PROFIT_LOST = "profit_lost";

	public static final String CHECK_NOTE = "check_note";

	public static final String CHECK_BY = "check_by";

	public static final String ORIGINATOR_ID = "originator_id";

	public static final String FIELD1 = "field1";

	public static final String FIELD2 = "field2";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Check{" +
			"id=" + id +
			", checkCode=" + checkCode +
			", checkTime=" + checkTime +
			", profitLost=" + profitLost +
			", checkNote=" + checkNote +
			", checkBy=" + checkBy +
			", originatorId=" + originatorId +
			", field1=" + field1 +
			", field2=" + field2 +
			"}";
	}
}
