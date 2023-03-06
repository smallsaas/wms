package com.jfeat.am.module.sku.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-18
 */
@TableName("wms_sku_photo")
public class SkuPhoto extends Model<SkuPhoto> {

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
	@TableField("sku_id")
	private Long skuId;


	public Long getId() {
		return id;
	}

	public SkuPhoto setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public SkuPhoto setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public SkuPhoto setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public SkuPhoto setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
		return this;
	}

	public Integer getIsPrimary() {
		return isPrimary;
	}

	public SkuPhoto setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public SkuPhoto setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public static final String ID = "id";

	public static final String PHOTO_URL = "photo_url";

	public static final String IS_PRIMARY = "is_primary";

	public static final String SKU_ID = "sku_id";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuPhoto{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", photoUrl='" + photoUrl + '\'' +
				", isPrimary=" + isPrimary +
				", skuId=" + skuId +
				'}';
	}
}
