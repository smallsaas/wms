package com.jfeat.am.module.warehouse.services.persistence.model;

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
 * @since 2018-08-29
 */
@TableName("wms_storage_in_item")
public class StorageInItem extends Model<StorageInItem> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 入库ID
     */
	@TableField("storage_in_id")
	private Long storageInId;
    /**
     * SKUID
     */
	@TableField("sku_id")
	private Long skuId;
    /**
     * 出入库价格
     */
	@TableField("transaction_sku_price")
	private BigDecimal transactionSkuPrice;
    /**
     * 操作数量
     */
	@TableField("transaction_quantities")
	private Integer transactionQuantities;
    /**
     * 操作后数量
     */
	@TableField("after_transaction_quantities")
	private Integer afterTransactionQuantities;
    /**
     * 操作时间
     */
	@TableField("transaction_time")
	private Date transactionTime;
    /**
     * 操作类型
     */
	private String type;
    /**
     * 关联的操作编号
     */
	@TableField("relation_code")
	private String relationCode;

	/**
	 * 需求数量
	 */
	@TableField("demand_quantities")
	private Integer demandQuantities;

	public Long getId() {
		return id;
	}

	public StorageInItem setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getStorageInId() {
		return storageInId;
	}

	public StorageInItem setStorageInId(Long storageInId) {
		this.storageInId = storageInId;
		return this;
	}

	public Long getSkuId() {
		return skuId;
	}

	public StorageInItem setSkuId(Long skuId) {
		this.skuId = skuId;
		return this;
	}

	public BigDecimal getTransactionSkuPrice() {
		return transactionSkuPrice;
	}

	public StorageInItem setTransactionSkuPrice(BigDecimal transactionSkuPrice) {
		this.transactionSkuPrice = transactionSkuPrice;
		return this;
	}

	public Integer getTransactionQuantities() {
		return transactionQuantities;
	}

	public StorageInItem setTransactionQuantities(Integer transactionQuantities) {
		this.transactionQuantities = transactionQuantities;
		return this;
	}

	public Integer getAfterTransactionQuantities() {
		return afterTransactionQuantities;
	}

	public StorageInItem setAfterTransactionQuantities(Integer afterTransactionQuantities) {
		this.afterTransactionQuantities = afterTransactionQuantities;
		return this;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public StorageInItem setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
		return this;
	}

	public String getType() {
		return type;
	}

	public StorageInItem setType(String type) {
		this.type = type;
		return this;
	}

	public String getRelationCode() {
		return relationCode;
	}

	public StorageInItem setRelationCode(String relationCode) {
		this.relationCode = relationCode;
		return this;
	}

	public Integer getDemandQuantities() {
		return demandQuantities;
	}

	public void setDemandQuantities(Integer demandQuantities) {
		this.demandQuantities = demandQuantities;
	}

	public static final String ID = "id";

	public static final String STORAGE_IN_ID = "storage_in_id";

	public static final String SKU_ID = "sku_id";

	public static final String TRANSACTION_SKU_PRICE = "transaction_sku_price";

	public static final String TRANSACTION_QUANTITIES = "transaction_quantities";

	public static final String AFTER_TRANSACTION_QUANTITIES = "after_transaction_quantities";

	public static final String TRANSACTION_TIME = "transaction_time";

	public static final String TYPE = "type";

	public static final String RELATION_CODE = "relation_code";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StorageInItem{" +
			"id=" + id +
			", storageInId=" + storageInId +
			", skuId=" + skuId +
			", transactionSkuPrice=" + transactionSkuPrice +
			", transactionQuantities=" + transactionQuantities +
			", afterTransactionQuantities=" + afterTransactionQuantities +
			", transactionTime=" + transactionTime +
			", type=" + type +
			", relationCode=" + relationCode +
			"}";
	}
}
