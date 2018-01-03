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
@TableName("so_member_care")
public class MemberCare extends Model<MemberCare> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Long id;
    /**
     * 外键/用户
     */
	@TableField("member_id")
	private Long memberId;
	/*
	* 	角色  member_id 以及 roles 组合为 KEY
	* */
	@TableField("roles")
	private String roles;
    /**
     * 外键/用户/粉丝ID
     */
	@TableField("follower_id")
	private Long followerId;
    /**
     * 关注时间
     */
	@TableField("follow_time")
	private Date followTime;


	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

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

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	public Date getFollowTime() {
		return followTime;
	}

	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MemberCare{" +
			"id=" + id +
			", memberId=" + memberId +
			", followerId=" + followerId +
			", followTime=" + followTime +
			"}";
	}
}
