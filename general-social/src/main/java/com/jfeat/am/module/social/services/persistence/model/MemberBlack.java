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
@TableName("so_member_black")
public class MemberBlack extends Model<MemberBlack> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Long id;
    /**
     * 拉黑用户
     */
	@TableField("member_id")
	private Long memberId;
    /**
     * 被拉黑用户
     */
	@TableField("member_black_id")
	private Long memberBlackId;
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

	public Long getMemberBlackId() {
		return memberBlackId;
	}

	public void setMemberBlackId(Long memberBlackId) {
		this.memberBlackId = memberBlackId;
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
		return "MemberBlack{" +
			"id=" + id +
			", memberId=" + memberId +
			", memberBlackId=" + memberBlackId +
			", createTime=" + createTime +
			", cause=" + cause +
			"}";
	}
}
