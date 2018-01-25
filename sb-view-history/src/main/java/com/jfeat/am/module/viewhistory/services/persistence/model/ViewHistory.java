package com.jfeat.am.module.viewhistory.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 浏览记录表
 * </p>
 *
 * @author Code Generator
 * @since 2018-01-23
 */
@TableName("t_view_history")
public class ViewHistory extends Model<ViewHistory> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Long id;
    /**
     * 外键/用户
     */
	@NotNull
	@TableField("member_id")
	private Long memberId;
    /**
     * 商店或者料理店的ID
     */
	@NotNull
	@TableField("roles_id")
	private Long rolesId;
    /**
     * 角色 与 ID 组合 KEY 
     */
	@NotEmpty
	private String roles;
    /**
     * 浏览时间
     */
	@NotEmpty
	@TableField("view_time")
	private Date viewTime;


	public Long getId() {
		return id;
	}

	public ViewHistory setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getMemberId() {
		return memberId;
	}

	public ViewHistory setMemberId(Long memberId) {
		this.memberId = memberId;
		return this;
	}

	public Long getRolesId() {
		return rolesId;
	}

	public ViewHistory setRolesId(Long rolesId) {
		this.rolesId = rolesId;
		return this;
	}

	public String getRoles() {
		return roles;
	}

	public ViewHistory setRoles(String roles) {
		this.roles = roles;
		return this;
	}

	public Date getViewTime() {
		return viewTime;
	}

	public ViewHistory setViewTime(Date viewTime) {
		this.viewTime = viewTime;
		return this;
	}

	public static final String ID = "id";

	public static final String MEMBER_ID = "member_id";

	public static final String ROLES_ID = "roles_id";

	public static final String ROLES = "roles";

	public static final String VIEW_TIME = "view_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ViewHistory{" +
			"id=" + id +
			", memberId=" + memberId +
			", rolesId=" + rolesId +
			", roles=" + roles +
			", viewTime=" + viewTime +
			"}";
	}
}
