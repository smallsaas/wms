package com.jfeat.am.module.product.services.persistence.model;

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
 * @since 2018-06-19
 */
@TableName("t_condition")
public class Condition extends Model<Condition> {

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
     * 父级ID
     */
	private Long pid;
    /**
     * 名称说明
     */
	@TableField("condition_description")
	private String conditionDescription;


	public Long getId() {
		return id;
	}

	public Condition setId(Long id) {
		this.id = id;
		return this;
	}

	public String getConditionName() {
		return conditionName;
	}

	public Condition setConditionName(String conditionName) {
		this.conditionName = conditionName;
		return this;
	}

	public Long getPid() {
		return pid;
	}

	public Condition setPid(Long pid) {
		this.pid = pid;
		return this;
	}

	public String getConditionDescription() {
		return conditionDescription;
	}

	public Condition setConditionDescription(String conditionDescription) {
		this.conditionDescription = conditionDescription;
		return this;
	}

	public static final String ID = "id";

	public static final String CONDITION_NAME = "condition_name";

	public static final String PID = "pid";

	public static final String CONDITION_DESCRIPTION = "condition_description";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Condition{" +
			"id=" + id +
			", conditionName=" + conditionName +
			", pid=" + pid +
			", conditionDescription=" + conditionDescription +
			"}";
	}
}
