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
@TableName("so_member_report")
public class MemberReport extends Model<MemberReport> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Long id;
    /**
     * 举报用户
     */
	@TableField("member_id")
	private Long memberId;
    /**
     * 被举报资源ID(用户/动态/活动)
     */
	@TableField("reference_id")
	private Long referenceId;
    /**
     * 举报原因 [独立表]
     */
	@TableField("cause_id")
	private String causeId;
    /**
     * 举报内容
     */
	private String desc;
    /**
     * 举报时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 处理结果
     */
	@TableField("deal_result")
	private String dealResult;
    /**
     * 处理时间
     */
	@TableField("deal_time")
	private Date dealTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public String getCauseId() {
		return causeId;
	}

	public void setCauseId(String causeId) {
		this.causeId = causeId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MemberReport{" +
			"id=" + id +
			", memberId=" + memberId +
			", referenceId=" + referenceId +
			", causeId=" + causeId +
			", desc=" + desc +
			", createTime=" + createTime +
			", dealResult=" + dealResult +
			", dealTime=" + dealTime +
			"}";
	}
}
