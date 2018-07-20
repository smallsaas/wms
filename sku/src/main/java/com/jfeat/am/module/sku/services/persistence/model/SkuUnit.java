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
@TableName("t_sku_unit")
public class SkuUnit extends Model<SkuUnit> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 类型名称
     */
	@TableField("unit_name")
	private String unitName;
    /**
     * 是否为主要计量单位
     */
	@TableField("is_primary")
	private Integer isPrimary;
    /**
     * 产品Id
     */
	@TableField("sku_id")
	private Long skuId;


	public Long getId() {
		return id;
	}

	public SkuUnit setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUnitName() {
		return unitName;
	}

	public SkuUnit setUnitName(String unitName) {
		this.unitName = unitName;
		return this;
	}

	public Integer getIsPrimary() {
		return isPrimary;
	}

	public SkuUnit setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public SkuUnit setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public static final String ID = "id";

	public static final String UNIT_NAME = "unit_name";

	public static final String IS_PRIMARY = "is_primary";

	public static final String SKU_ID = "sku_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuUnit{" +
			"id=" + id +
			", unitName=" + unitName +
			", isPrimary=" + isPrimary +
			", skuId=" + skuId +
			"}";
	}
}
