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
@TableName("t_sku_tag_relation")
public class SkuTagRelation extends Model<SkuTagRelation> {

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
	@TableField("sku_id")
	private Long skuId;
    /**
     * 标签ID
     */
	@TableField("tag_id")
	private Long tagId;


	public Long getId() {
		return id;
	}

	public SkuTagRelation setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public SkuTagRelation setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public Long getTagId() {
		return tagId;
	}

	public SkuTagRelation setTagId(Long tagId) {
		this.tagId = tagId;
		return this;
	}

	public static final String ID = "id";

	public static final String SKU_ID = "sku_id";

	public static final String TAG_ID = "tag_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuTagRelation{" +
			"id=" + id +
			", skuId=" + skuId +
			", tagId=" + tagId +
			"}";
	}
}
