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
@TableName("t_sku_product")
public class SkuProduct extends Model<SkuProduct> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 产品名称
     */
	@TableField("product_id")
	private Long productId;
    /**
     * sku编号
     */
	@TableField("sku_code")
	private String skuCode;
    /**
     * sku名称
     */
	@TableField("sku_name")
	private String skuName;
    /**
     * 状态
     */
	private String status;
    /**
     * 排序值
     */
	@TableField("sort_value")
	private Integer sortValue;
    /**
     * 搜索关键字
     */
	@TableField("search_key_word")
	private String searchKeyWord;
    /**
     * 条形码
     */
	@TableField("bar_code")
	private String barCode;
    /**
     * 描述
     */
	private String description;
    /**
     * 产品价格
     */
	@TableField("sku_price")
	private BigDecimal skuPrice;
    /**
     * 入库成本调整
     */
	@TableField("readjust_cost_price")
	private BigDecimal readjustCostPrice;
    /**
     * 建议零售价
     */
	@TableField("suggested_price")
	private BigDecimal suggestedPrice;
    /**
     * 成本价格
     */
	@TableField("cost_price")
	private BigDecimal costPrice;
    /**
     * 库存成本
     */
	@TableField("stock_cost")
	private BigDecimal stockCost;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;
	/**
	 * 质量
	 */
	@TableField("weight")
	private String weight;
	/**
	 * 体积
	 */
	@TableField("volume")
	private String volume;
	/**
	 * 规格
	 */
	@TableField("spec")
	private String spec;
    /**
     * 保留字段
     */
	private String field1;
    /**
     * 保留字段
     */
	private String field2;
    /**
     * 保留字段
     */
	private String field3;


	public Long getId() {
		return id;
	}

	public SkuProduct setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getProductId() {
		return productId;
	}

	public SkuProduct setProductId(Long productId) {
		this.productId = productId;
		return this;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public SkuProduct setSkuCode(String skuCode) {
		this.skuCode = skuCode;
		return this;
	}

	public String getSkuName() {
		return skuName;
	}

	public SkuProduct setSkuName(String skuName) {
		this.skuName = skuName;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public SkuProduct setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getSortValue() {
		return sortValue;
	}

	public SkuProduct setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
		return this;
	}

	public String getSearchKeyWord() {
		return searchKeyWord;
	}

	public SkuProduct setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
		return this;
	}

	public String getBarCode() {
		return barCode;
	}

	public SkuProduct setBarCode(String barCode) {
		this.barCode = barCode;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public SkuProduct setDescription(String description) {
		this.description = description;
		return this;
	}

	public BigDecimal getSkuPrice() {
		return skuPrice;
	}

	public SkuProduct setSkuPrice(BigDecimal skuPrice) {
		this.skuPrice = skuPrice;
		return this;
	}

	public BigDecimal getReadjustCostPrice() {
		return readjustCostPrice;
	}

	public SkuProduct setReadjustCostPrice(BigDecimal readjustCostPrice) {
		this.readjustCostPrice = readjustCostPrice;
		return this;
	}

	public BigDecimal getSuggestedPrice() {
		return suggestedPrice;
	}

	public SkuProduct setSuggestedPrice(BigDecimal suggestedPrice) {
		this.suggestedPrice = suggestedPrice;
		return this;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public SkuProduct setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
		return this;
	}

	public BigDecimal getStockCost() {
		return stockCost;
	}

	public SkuProduct setStockCost(BigDecimal stockCost) {
		this.stockCost = stockCost;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public SkuProduct setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public SkuProduct setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getField1() {
		return field1;
	}

	public SkuProduct setField1(String field1) {
		this.field1 = field1;
		return this;
	}

	public String getField2() {
		return field2;
	}

	public SkuProduct setField2(String field2) {
		this.field2 = field2;
		return this;
	}

	public String getField3() {
		return field3;
	}

	public SkuProduct setField3(String field3) {
		this.field3 = field3;
		return this;
	}

	public static final String ID = "id";

	public static final String PRODUCT_ID = "product_id";

	public static final String SKU_CODE = "sku_code";

	public static final String SKU_NAME = "sku_name";

	public static final String STATUS = "status";

	public static final String SORT_VALUE = "sort_value";

	public static final String SEARCH_KEY_WORD = "search_key_word";

	public static final String BAR_CODE = "bar_code";

	public static final String DESCRIPTION = "description";

	public static final String SKU_PRICE = "sku_price";

	public static final String READJUST_COST_PRICE = "readjust_cost_price";

	public static final String SUGGESTED_PRICE = "suggested_price";

	public static final String COST_PRICE = "cost_price";

	public static final String STOCK_COST = "stock_cost";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String SPEC = "spec";

	public static final String WEIGHT = "weight";

	public static final String VOLUME = "volume";

	public static final String FIELD1 = "field1";

	public static final String FIELD2 = "field2";

	public static final String FIELD3 = "field3";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SkuProduct{" +
			"id=" + id +
			", productId=" + productId +
			", skuCode=" + skuCode +
			", skuName=" + skuName +
			", status=" + status +
			", sortValue=" + sortValue +
			", searchKeyWord=" + searchKeyWord +
			", barCode=" + barCode +
			", description=" + description +
			", skuPrice=" + skuPrice +
			", readjustCostPrice=" + readjustCostPrice +
			", suggestedPrice=" + suggestedPrice +
			", costPrice=" + costPrice +
			", stockCost=" + stockCost +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
				", weight=" + weight +
				", volume=" + volume +
				", spec=" + spec +
			", field1=" + field1 +
			", field2=" + field2 +
			", field3=" + field3 +
			"}";
	}
}
