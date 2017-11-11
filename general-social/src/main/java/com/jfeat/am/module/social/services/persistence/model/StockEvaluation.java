package com.jfeat.am.module.social.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-11-11
 */
@TableName("so_stock_evaluation")
public class StockEvaluation extends Model<StockEvaluation> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
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
     * 评价信息
     */
	private String note;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

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
			", note=" + note +
			", createTime=" + createTime +
			"}";
	}
}
