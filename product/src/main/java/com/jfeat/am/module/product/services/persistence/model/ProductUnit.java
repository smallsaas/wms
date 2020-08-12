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
@TableName("t_product_unit")
public class ProductUnit extends Model<ProductUnit> {

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
	@TableField("product_id")
	private Long productId;


	public Long getId() {
		return id;
	}

	public ProductUnit setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public ProductUnit setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public ProductUnit setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getUnitName() {
		return unitName;
	}

	public ProductUnit setUnitName(String unitName) {
		this.unitName = unitName;
		return this;
	}

	public Integer getIsPrimary() {
		return isPrimary;
	}

	public ProductUnit setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
		return this;
	}

	public Long getProductId() {
		return productId;
	}

	public ProductUnit setProductId(Long productId) {
		this.productId = productId;
		return this;
	}

	public static final String ID = "id";

	public static final String UNIT_NAME = "unit_name";

	public static final String IS_PRIMARY = "is_primary";

	public static final String PRODUCT_ID = "product_id";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductUnit{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", unitName='" + unitName + '\'' +
				", isPrimary=" + isPrimary +
				", productId=" + productId +
				'}';
	}
}
