package com.jfeat.am.module.product.services.persistence.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-19
 */
@TableName("wms_producwms_condition")
public class ProductCondition extends Model<ProductCondition> {

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
	@TableField("product_id")
	private Long productId;
    /**
     * 标签ID
     */
	@TableField("condition_id")
	private Long conditionId;


	public Long getId() {
		return id;
	}

	public ProductCondition setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public ProductCondition setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public ProductCondition setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public Long getProductId() {
		return productId;
	}

	public ProductCondition setProductId(Long productId) {
		this.productId = productId;
		return this;
	}

	public Long getConditionId() {
		return conditionId;
	}

	public ProductCondition setConditionId(Long conditionId) {
		this.conditionId = conditionId;
		return this;
	}

	public static final String ID = "id";

	public static final String PRODUCT_ID = "product_id";

	public static final String CONDITION_ID = "condition_id";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductCondition{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", productId=" + productId +
				", conditionId=" + conditionId +
				'}';
	}
}
