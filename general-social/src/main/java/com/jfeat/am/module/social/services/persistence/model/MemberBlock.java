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
@TableName("so_member_block")
public class MemberBlock extends Model<MemberBlock> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Long id;
    /**
     * 屏蔽用户
     */
	@TableField("member_id")
	private Long memberId;
    /**
     * 被屏蔽的ID
     */
	@TableField("reference_id")
	private Long referenceId;
    /**
     * 拉黑时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 通用字段
     */
	private String cause;


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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MemberBlock{" +
			"id=" + id +
			", memberId=" + memberId +
			", referenceId=" + referenceId +
			", createTime=" + createTime +
			", cause=" + cause +
			"}";
	}
}
