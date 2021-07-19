package com.jfeat.am.module.sku.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import java.math.BigDecimal;
import java.util.Date;
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
@TableName("wms_sku_price_history")
public class SkuPriceHistory extends Model<SkuPriceHistory> {

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
     * 单位Id
     */
	@TableField("sku_id")
	private Long skuId;
    /**
     * 原来价格
     */
	@TableField("origin_price")
	private BigDecimal originPrice;
    /**
     * 更新后价格
     */
	@TableField("after_price")
	private BigDecimal afterPrice;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public SkuPriceHistory setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public SkuPriceHistory setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public SkuPriceHistory setOrgTag(String orgTag) {
		this.orgTag = orgTag;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public SkuPriceHistory setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public BigDecimal getOriginPrice() {
		return originPrice;
	}

	public SkuPriceHistory setOriginPrice(BigDecimal originPrice) {
		this.originPrice = originPrice;
		return this;
	}

	public BigDecimal getAfterPrice() {
		return afterPrice;
	}

	public SkuPriceHistory setAfterPrice(BigDecimal afterPrice) {
		this.afterPrice = afterPrice;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public SkuPriceHistory setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String SKU_ID = "sku_id";

	public static final String ORIGIN_PRICE = "origin_price";

	public static final String AFTER_PRICE = "after_price";

	public static final String UPDATE_TIME = "update_time";

	public static final String ORG_ID = "org_id";

	public static final String ORG_TAG = "org_tag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuPriceHistory{" +
				"id=" + id +
				", orgId=" + orgId +
				", orgTag='" + orgTag + '\'' +
				", skuId=" + skuId +
				", originPrice=" + originPrice +
				", afterPrice=" + afterPrice +
				", updateTime=" + updateTime +
				'}';
	}
}
