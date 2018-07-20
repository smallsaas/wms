package com.jfeat.am.module.sku.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
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
@TableName("wms_sku_price_history")
public class SkuPriceHistory extends Model<SkuPriceHistory> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuPriceHistory{" +
			"id=" + id +
			", skuId=" + skuId +
			", originPrice=" + originPrice +
			", afterPrice=" + afterPrice +
			", updateTime=" + updateTime +
			"}";
	}
}
