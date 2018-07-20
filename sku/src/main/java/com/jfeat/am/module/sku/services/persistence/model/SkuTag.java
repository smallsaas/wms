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
@TableName("t_sku_tag")
public class SkuTag extends Model<SkuTag> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuTag{" +
			"id=" + id +
			", tagName=" + tagName +
			", tagDescription=" + tagDescription +
			"}";
	}
}
