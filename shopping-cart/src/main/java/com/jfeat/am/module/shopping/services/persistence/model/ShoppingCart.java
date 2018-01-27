package com.jfeat.am.module.shopping.services.persistence.model;

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
 * @since 2018-01-27
 */
@TableName("t_shopping_cart")
public class ShoppingCart extends Model<ShoppingCart> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 产品ID
     */
	@TableField("product_id")
	private Integer productId;
    /**
     * 产品名称
     */
	@TableField("product_name")
	private String productName;
    /**
     * 产品封面
     */
	private String cover;
    /**
     * 产品数量
     */
	private Integer quantity;
    /**
     * 总价
     */
	@TableField("total_price")
	private BigDecimal totalPrice;
    /**
     * 创建时间
     */
	@TableField("created_date")
	private Date createdDate;
    /**
     * 产品规格ID
     */
	@TableField("product_specification_id")
	private Integer productSpecificationId;
    /**
     * 规格名称
     */
	@TableField("product_specification_name")
	private String productSpecificationName;
    /**
     * 经营者ID
     */
	@TableField("fare_id")
	private Integer fareId;
    /**
     * 重量
     */
	private Integer weight;
    /**
     * 是否批发
     */
	private Integer bulk;


	public Integer getId() {
		return id;
	}

	public ShoppingCart setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public ShoppingCart setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public Integer getProductId() {
		return productId;
	}

	public ShoppingCart setProductId(Integer productId) {
		this.productId = productId;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public ShoppingCart setProductName(String productName) {
		this.productName = productName;
		return this;
	}

	public String getCover() {
		return cover;
	}

	public ShoppingCart setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public ShoppingCart setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public ShoppingCart setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public ShoppingCart setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Integer getProductSpecificationId() {
		return productSpecificationId;
	}

	public ShoppingCart setProductSpecificationId(Integer productSpecificationId) {
		this.productSpecificationId = productSpecificationId;
		return this;
	}

	public String getProductSpecificationName() {
		return productSpecificationName;
	}

	public ShoppingCart setProductSpecificationName(String productSpecificationName) {
		this.productSpecificationName = productSpecificationName;
		return this;
	}

	public Integer getFareId() {
		return fareId;
	}

	public ShoppingCart setFareId(Integer fareId) {
		this.fareId = fareId;
		return this;
	}

	public Integer getWeight() {
		return weight;
	}

	public ShoppingCart setWeight(Integer weight) {
		this.weight = weight;
		return this;
	}

	public Integer getBulk() {
		return bulk;
	}

	public ShoppingCart setBulk(Integer bulk) {
		this.bulk = bulk;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String PRODUCT_ID = "product_id";

	public static final String PRODUCT_NAME = "product_name";

	public static final String COVER = "cover";

	public static final String QUANTITY = "quantity";

	public static final String TOTAL_PRICE = "total_price";

	public static final String CREATED_DATE = "created_date";

	public static final String PRODUCT_SPECIFICATION_ID = "product_specification_id";

	public static final String PRODUCT_SPECIFICATION_NAME = "product_specification_name";

	public static final String FARE_ID = "fare_id";

	public static final String WEIGHT = "weight";

	public static final String BULK = "bulk";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ShoppingCart{" +
			"id=" + id +
			", userId=" + userId +
			", productId=" + productId +
			", productName=" + productName +
			", cover=" + cover +
			", quantity=" + quantity +
			", totalPrice=" + totalPrice +
			", createdDate=" + createdDate +
			", productSpecificationId=" + productSpecificationId +
			", productSpecificationName=" + productSpecificationName +
			", fareId=" + fareId +
			", weight=" + weight +
			", bulk=" + bulk +
			"}";
	}
}
