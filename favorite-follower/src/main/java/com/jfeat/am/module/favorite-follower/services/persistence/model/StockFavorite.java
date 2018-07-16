package com.jfeat.am.module.product.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-07-16
 */
@TableName("t_stock_favorite")
public class StockFavorite extends Model<StockFavorite> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户ID
     */
	@TableField("member_id")
	private Long memberId;
    /**
     * 用户ID
     */
	@TableField("member_name")
	private Long memberName;
    /**
     * 收藏对象ID
     */
	@TableField("stock_id")
	private Long stockId;
    /**
     * 收藏对象
     */
	@TableField("stock_name")
	private Long stockName;
    /**
     * 收藏类型类型
     */
	@TableField("stock_type")
	private String stockType;
    /**
     * 收藏时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public StockFavorite setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getMemberId() {
		return memberId;
	}

	public StockFavorite setMemberId(Long memberId) {
		this.memberId = memberId;
		return this;
	}

	public Long getMemberName() {
		return memberName;
	}

	public StockFavorite setMemberName(Long memberName) {
		this.memberName = memberName;
		return this;
	}

	public Long getStockId() {
		return stockId;
	}

	public StockFavorite setStockId(Long stockId) {
		this.stockId = stockId;
		return this;
	}

	public Long getStockName() {
		return stockName;
	}

	public StockFavorite setStockName(Long stockName) {
		this.stockName = stockName;
		return this;
	}

	public String getStockType() {
		return stockType;
	}

	public StockFavorite setStockType(String stockType) {
		this.stockType = stockType;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public StockFavorite setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String MEMBER_ID = "member_id";

	public static final String MEMBER_NAME = "member_name";

	public static final String STOCK_ID = "stock_id";

	public static final String STOCK_NAME = "stock_name";

	public static final String STOCK_TYPE = "stock_type";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StockFavorite{" +
			"id=" + id +
			", memberId=" + memberId +
			", memberName=" + memberName +
			", stockId=" + stockId +
			", stockName=" + stockName +
			", stockType=" + stockType +
			", createTime=" + createTime +
			"}";
	}
}
