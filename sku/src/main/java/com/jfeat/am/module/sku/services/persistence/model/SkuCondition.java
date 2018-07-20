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
@TableName("t_sku_condition")
public class SkuCondition extends Model<SkuCondition> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuCondition{" +
			"id=" + id +
			", conditionName=" + conditionName +
			", conditionDescription=" + conditionDescription +
			"}";
	}
}
