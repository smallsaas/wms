package com.jfeat.am.module.evaluation.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */
@TableName("t_stock_evaluation")
public class StockEvaluation extends Model<StockEvaluation> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 外键
     */
	@TableField("stock_id")
	private Long stockId;
    /**
     * 外键
     */
	@TableField("member_id")
	private Long memberId;
    /**
     * 类型 评价为 商品/订单/其他
     */
	private String type;
    /**
     * 评价信息
     */
	private String note;
    /**
     * 默认显示
     */
	@TableField("is_display")
	private Integer isDisplay;
    /**
     * 是否置顶 默认不置顶
     */
	@TableField("is_stick")
	private Integer isStick;
    /**
     * 状态
     */
	private String status;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public StockEvaluation setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getStockId() {
		return stockId;
	}

	public StockEvaluation setStockId(Long stockId) {
		this.stockId = stockId;
		return this;
	}

	public Long getMemberId() {
		return memberId;
	}

	public StockEvaluation setMemberId(Long memberId) {
		this.memberId = memberId;
		return this;
	}

	public String getType() {
		return type;
	}

	public StockEvaluation setType(String type) {
		this.type = type;
		return this;
	}

	public String getNote() {
		return note;
	}

	public StockEvaluation setNote(String note) {
		this.note = note;
		return this;
	}

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public StockEvaluation setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
		return this;
	}

	public Integer getIsStick() {
		return isStick;
	}

	public StockEvaluation setIsStick(Integer isStick) {
		this.isStick = isStick;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public StockEvaluation setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public StockEvaluation setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String STOCK_ID = "stock_id";

	public static final String MEMBER_ID = "member_id";

	public static final String TYPE = "type";

	public static final String NOTE = "note";

	public static final String IS_DISPLAY = "is_display";

	public static final String IS_STICK = "is_stick";

	public static final String STATUS = "status";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StockEvaluation{" +
			"id=" + id +
			", stockId=" + stockId +
			", memberId=" + memberId +
			", type=" + type +
			", note=" + note +
			", isDisplay=" + isDisplay +
			", isStick=" + isStick +
			", status=" + status +
			", createTime=" + createTime +
			"}";
	}
}
