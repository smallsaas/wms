package com.jfeat.am.module.sku.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2018-07-18
 */
@TableName("t_sku_condition_relation")
public class SkuConditionRelation extends Model<SkuConditionRelation> {

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
     * 产品Id
     */
	@TableField("sku_id")
	private Long skuId;
    /**
     * 标签ID
     */
	@TableField("condition_id")
	private Long conditionId;


	public Long getId() {
		return id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public SkuConditionRelation setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public SkuConditionRelation setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public SkuConditionRelation setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public SkuConditionRelation setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public Long getConditionId() {
		return conditionId;
	}

	public SkuConditionRelation setConditionId(Long conditionId) {
		this.conditionId = conditionId;
		return this;
	}

	public static final String ID = "id";

	public static final String SKU_ID = "sku_id";

	public static final String CONDITION_ID = "condition_id";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuConditionRelation{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", skuId=" + skuId +
				", conditionId=" + conditionId +
				'}';
	}
}
