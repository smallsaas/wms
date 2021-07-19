package com.jfeat.am.module.product.services.persistence.model;

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
 * @since 2018-06-19
 */
@TableName("t_specification")
public class Specification extends Model<Specification> {

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
     * 规格名称
     */
	@TableField("specification_name")
	private String specificationName;
    /**
     * 规格值
     */
	@TableField("specification_value")
	private String specificationValue;
    /**
     * 组ID
     */
	@TableField("group_id")
	private Long groupId;
    /**
     * 产品ID
     */
	@TableField("product_id")
	private Long productId;


	public Long getId() {
		return id;
	}

	public Specification setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Specification setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public Specification setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getSpecificationName() {
		return specificationName;
	}

	public Specification setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
		return this;
	}

	public String getSpecificationValue() {
		return specificationValue;
	}

	public Specification setSpecificationValue(String specificationValue) {
		this.specificationValue = specificationValue;
		return this;
	}

	public Long getGroupId() {
		return groupId;
	}

	public Specification setGroupId(Long groupId) {
		this.groupId = groupId;
		return this;
	}

	public Long getProductId() {
		return productId;
	}

	public Specification setProductId(Long productId) {
		this.productId = productId;
		return this;
	}

	public static final String ID = "id";

	public static final String SPECIFICATION_NAME = "specification_name";

	public static final String SPECIFICATION_VALUE = "specification_value";

	public static final String GROUP_ID = "group_id";

	public static final String PRODUCT_ID = "product_id";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Specification{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", specificationName='" + specificationName + '\'' +
				", specificationValue='" + specificationValue + '\'' +
				", groupId=" + groupId +
				", productId=" + productId +
				'}';
	}
}
