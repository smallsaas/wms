package com.jfeat.am.module.warehouse.services.persistence.model;

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
 * @since 2018-06-30
 */
@TableName("wms_suppliers_product")
public class SuppliersProduct extends Model<SuppliersProduct> {

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
     * 供应商ID
     */
	@TableField("suppliers_id")
	private Long suppliersId;
    /**
     * 商品ID
     */
	@TableField("product_id")
	private Long productId;


	public Long getId() {
		return id;
	}

	public SuppliersProduct setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public SuppliersProduct setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public SuppliersProduct setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public Long getSuppliersId() {
		return suppliersId;
	}

	public SuppliersProduct setSuppliersId(Long suppliersId) {
		this.suppliersId = suppliersId;
		return this;
	}

	public Long getProductId() {
		return productId;
	}

	public SuppliersProduct setProductId(Long productId) {
		this.productId = productId;
		return this;
	}

	public static final String ID = "id";

	public static final String SUPPLIERS_ID = "suppliers_id";

	public static final String PRODUCT_ID = "product_id";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SuppliersProduct{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", suppliersId=" + suppliersId +
				", productId=" + productId +
				'}';
	}
}
