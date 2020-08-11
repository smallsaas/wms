package com.jfeat.am.module.product.services.persistence.model;

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
 * @since 2018-06-19
 */
@TableName("t_product")
public class Product extends Model<Product> {

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
     * 产品类别
     */
	@TableField("product_category_id")
	private Long productCategoryId;
    /**
     * 编码
     */
	@TableField("product_code")
	private String productCode;
    /**
     * 名称
     */
	private String name;
    /**
     * 外文名称
     */
	@TableField("english_name")
	private String englishName;
    /**
     * 重量
     */
	private String weight;
    /**
     * 入库成本调整
     */
	@TableField("readjust_cost_price")
	private BigDecimal readjustCostPrice;
    /**
     * 产品标准
     */
	@TableField("product_standard")
	private String productStandard;
    /**
     * 价格(该场景下是零售价格)
     */
	private BigDecimal price;
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
     * 规格
     */
	private String specification;
    /**
     * 数量
     */
	private Integer quantities;
    /**
     * 库存成本
     */
	@TableField("stock_cost")
	private BigDecimal stockCost;
    /**
     * 预购数量
     */
	@TableField("purchase_advance")
	private Integer purchaseAdvance;
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
     * 描述
     */
	private String description;
    /**
     * 保留字段
     */
	private String field1;
    /**
     * 保留字段
     */
	private String volume;
    /**
     * 保留字段
     */
	private String spec;
    /**
     * 保留字段
     */
	private String field4;
    /**
     * 保留字段
     */
	private String field5;


	public Long getId() {
		return id;
	}

	public Product setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public Product setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
		return this;
	}


	public String getProductCode() {
		return productCode;
	}

	public Product setProductCode(String productCode) {
		this.productCode = productCode;
		return this;
	}

	public String getName() {
		return name;
	}

	public Product setName(String name) {
		this.name = name;
		return this;
	}

	public String getEnglishName() {
		return englishName;
	}

	public Product setEnglishName(String englishName) {
		this.englishName = englishName;
		return this;
	}

	public String getWeight() {
		return weight;
	}

	public Product setWeight(String weight) {
		this.weight = weight;
		return this;
	}

	public BigDecimal getReadjustCostPrice() {
		return readjustCostPrice;
	}

	public Product setReadjustCostPrice(BigDecimal readjustCostPrice) {
		this.readjustCostPrice = readjustCostPrice;
		return this;
	}

	public String getProductStandard() {
		return productStandard;
	}

	public Product setProductStandard(String productStandard) {
		this.productStandard = productStandard;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Product setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	public BigDecimal getSuggestedPrice() {
		return suggestedPrice;
	}

	public Product setSuggestedPrice(BigDecimal suggestedPrice) {
		this.suggestedPrice = suggestedPrice;
		return this;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public Product setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
		return this;
	}

	public String getSpecification() {
		return specification;
	}

	public Product setSpecification(String specification) {
		this.specification = specification;
		return this;
	}

	public Integer getQuantities() {
		return quantities;
	}

	public Product setQuantities(Integer quantities) {
		this.quantities = quantities;
		return this;
	}

	public BigDecimal getStockCost() {
		return stockCost;
	}

	public Product setStockCost(BigDecimal stockCost) {
		this.stockCost = stockCost;
		return this;
	}

	public Integer getPurchaseAdvance() {
		return purchaseAdvance;
	}

	public Product setPurchaseAdvance(Integer purchaseAdvance) {
		this.purchaseAdvance = purchaseAdvance;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Product setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getSortValue() {
		return sortValue;
	}

	public Product setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
		return this;
	}

	public String getSearchKeyWord() {
		return searchKeyWord;
	}

	public Product setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
		return this;
	}

	public String getBarCode() {
		return barCode;
	}

	public Product setBarCode(String barCode) {
		this.barCode = barCode;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Product setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Product setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Product setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getField1() {
		return field1;
	}

	public Product setField1(String field1) {
		this.field1 = field1;
		return this;
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

	public String getField4() {
		return field4;
	}

	public Product setField4(String field4) {
		this.field4 = field4;
		return this;
	}

	public String getField5() {
		return field5;
	}

	public Product setField5(String field5) {
		this.field5 = field5;
		return this;
	}

	public static final String ID = "id";

	public static final String PRODUCT_CATEGORY_ID = "product_category_id";


	public static final String PRODUCT_CODE = "product_code";

	public static final String NAME = "name";

	public static final String ENGLISH_NAME = "english_name";

	public static final String WEIGHT = "weight";

	public static final String READJUST_COST_PRICE = "readjust_cost_price";

	public static final String PRODUCT_STANDARD = "product_standard";

	public static final String PRICE = "price";

	public static final String SUGGESTED_PRICE = "suggested_price";

	public static final String COST_PRICE = "cost_price";

	public static final String SPECIFICATION = "specification";

	public static final String QUANTITIES = "quantities";

	public static final String STOCK_COST = "stock_cost";

	public static final String PURCHASE_ADVANCE = "purchase_advance";

	public static final String STATUS = "status";

	public static final String SORT_VALUE = "sort_value";

	public static final String SEARCH_KEY_WORD = "search_key_word";

	public static final String BAR_CODE = "bar_code";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String DESCRIPTION = "description";

	public static final String FIELD1 = "field1";

	public static final String VOLUME = "volume";

	public static final String SPEC = "spec";

	public static final String FIELD4 = "field4";

	public static final String FIELD5 = "field5";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Product{" +
			"id=" + id +
			", productCategoryId=" + productCategoryId +
			", productCode=" + productCode +
			", name=" + name +
			", englishName=" + englishName +
			", weight=" + weight +
			", readjustCostPrice=" + readjustCostPrice +
			", productStandard=" + productStandard +
			", price=" + price +
			", suggestedPrice=" + suggestedPrice +
			", costPrice=" + costPrice +
			", specification=" + specification +
			", quantities=" + quantities +
			", stockCost=" + stockCost +
			", purchaseAdvance=" + purchaseAdvance +
			", status=" + status +
			", sortValue=" + sortValue +
			", searchKeyWord=" + searchKeyWord +
			", barCode=" + barCode +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", description=" + description +
			", field1=" + field1 +
			", spec=" + spec +
			", volume=" + volume +
			", field4=" + field4 +
			", field5=" + field5 +
			"}";
	}
}
