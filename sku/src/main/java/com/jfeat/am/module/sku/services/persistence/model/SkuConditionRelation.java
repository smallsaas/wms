package com.jfeat.am.module.sku.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-07-18
 */
@TableName("t_sku_condition_relation")
public class SkuConditionRelation extends Model<SkuConditionRelation> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuConditionRelation{" +
			"id=" + id +
			", skuId=" + skuId +
			", conditionId=" + conditionId +
			"}";
	}
}
