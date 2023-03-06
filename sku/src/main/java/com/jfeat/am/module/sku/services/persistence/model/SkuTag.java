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
@TableName("wms_sku_tag")
public class SkuTag extends Model<SkuTag> {

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
     * 标签名称
     */
	@TableField("tag_name")
	private String tagName;
    /**
     * 标签说明
     */
	@TableField("tag_description")
	private String tagDescription;


	public Long getId() {
		return id;
	}

	public SkuTag setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public SkuTag setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public SkuTag setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public String getTagName() {
		return tagName;
	}

	public SkuTag setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

	public String getTagDescription() {
		return tagDescription;
	}

	public SkuTag setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
		return this;
	}

	public static final String ID = "id";

	public static final String TAG_NAME = "tag_name";

	public static final String TAG_DESCRIPTION = "tag_description";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuTag{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", tagName='" + tagName + '\'' +
				", tagDescription='" + tagDescription + '\'' +
				'}';
	}
}
