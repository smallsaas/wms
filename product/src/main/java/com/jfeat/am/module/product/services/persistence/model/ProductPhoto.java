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
@TableName("t_product_photo")
public class ProductPhoto extends Model<ProductPhoto> {

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
     * 图片地址
     */
	@TableField("photo_url")
	private String photoUrl;
    /**
     * 是否为主要
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

	public ProductPhoto setId(Long id) {
		this.id = id;
		return this;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public ProductPhoto setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
		return this;
	}

	public Integer getIsPrimary() {
		return isPrimary;
	}

	public ProductPhoto setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
		return this;
	}

	public Long getProductId() {
		return productId;
	}

	public ProductPhoto setProductId(Long productId) {
		this.productId = productId;
		return this;
	}

	public static final String ID = "id";

	public static final String PHOTO_URL = "photo_url";

	public static final String IS_PRIMARY = "is_primary";

	public static final String PRODUCT_ID = "product_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductPhoto{" +
			"id=" + id +
			", photoUrl=" + photoUrl +
			", isPrimary=" + isPrimary +
			", productId=" + productId +
			"}";
	}
}
