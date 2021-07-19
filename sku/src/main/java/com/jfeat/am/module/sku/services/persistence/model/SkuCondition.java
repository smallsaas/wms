package com.jfeat.am.module.sku.services.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-18
 */
@TableName("t_sku_condition")
public class SkuCondition extends Model<SkuCondition> {

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
     * 状态名称
     */
	@TableField("condition_name")
	private String conditionName;
    /**
     * 名称说明
     */
	@TableField("condition_description")
	private String conditionDescription;


	public Long getId() {
		return id;
	}

	public SkuCondition setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public SkuCondition setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public SkuCondition setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getConditionName() {
		return conditionName;
	}

	public SkuCondition setConditionName(String conditionName) {
		this.conditionName = conditionName;
		return this;
	}

	public String getConditionDescription() {
		return conditionDescription;
	}

	public SkuCondition setConditionDescription(String conditionDescription) {
		this.conditionDescription = conditionDescription;
		return this;
	}

	public static final String ID = "id";

	public static final String CONDITION_NAME = "condition_name";

	public static final String CONDITION_DESCRIPTION = "condition_description";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuCondition{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", conditionName='" + conditionName + '\'' +
				", conditionDescription='" + conditionDescription + '\'' +
				'}';
	}
}
