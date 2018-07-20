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
@TableName("t_sku_photo")
public class SkuPhoto extends Model<SkuPhoto> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuPhoto{" +
			"id=" + id +
			", photoUrl=" + photoUrl +
			", isPrimary=" + isPrimary +
			", skuId=" + skuId +
			"}";
	}
}
