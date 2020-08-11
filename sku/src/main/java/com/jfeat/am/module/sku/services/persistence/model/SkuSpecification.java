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
@TableName("t_sku_specification")
public class SkuSpecification extends Model<SkuSpecification> {

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
     * 组ID
     */
	@TableField("sku_id")
	private Long skuId;
    /**
     * 产品ID
     */
	@TableField("group_id")
	private Long groupId;


	public Long getId() {
		return id;
	}

	public SkuSpecification setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public SkuSpecification setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public Long getGroupId() {
		return groupId;
	}

	public SkuSpecification setGroupId(Long groupId) {
		this.groupId = groupId;
		return this;
	}

	public static final String ID = "id";

	public static final String SKU_ID = "sku_id";

	public static final String GROUP_ID = "group_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuSpecification{" +
			"id=" + id +
			", skuId=" + skuId +
			", groupId=" + groupId +
			"}";
	}
}
