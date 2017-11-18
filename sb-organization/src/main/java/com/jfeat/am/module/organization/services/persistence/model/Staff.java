package com.jfeat.am.module.organization.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
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
 * @since 2017-10-27
 */
@TableName("t_staff")
public class Staff extends Model<Staff> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * ????
     */
	@TableField("dept_id")
	private Long deptId;

	@TableField("user_id")
	private Long userId;

    /**
     * ??
     */
	@TableField("work_age")
	private Integer workAge;
    /**
     * ????
     */
	@TableField("hire_date")
	private Date hireDate;
    /**
     * ????
     */
	@TableField("leave_date")
	private Date leaveDate;
    /**
     * ????
     */
	private String status;
	@TableField("profile_id")
	private Long profileId;
    /**
     * ????
     */
	@TableField("position_id")
	private Long positionId;
    /**
     * ????
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * ??
     */
	private String note;


	public Long getId() {
		return id;
	}

	public Staff setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getDeptId() {
		return deptId;
	}

	public Staff setDeptId(Long deptId) {
		this.deptId = deptId;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getWorkAge() {
		return workAge;
	}

	public Staff setWorkAge(Integer workAge) {
		this.workAge = workAge;
		return this;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public Staff setHireDate(Date hireDate) {
		this.hireDate = hireDate;
		return this;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public Staff setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Staff setStatus(String status) {
		this.status = status;
		return this;
	}

	public Long getProfileId() {
		return profileId;
	}

	public Staff setProfileId(Long profileId) {
		this.profileId = profileId;
		return this;
	}

	public Long getPositionId() {
		return positionId;
	}

	public Staff setPositionId(Long positionId) {
		this.positionId = positionId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Staff setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getNote() {
		return note;
	}

	public Staff setNote(String note) {
		this.note = note;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String DEPT_ID = "dept_id";

	public static final String WORK_AGE = "work_age";

	public static final String HIRE_DATE = "hire_date";

	public static final String LEAVE_DATE = "leave_date";

	public static final String STATUS = "status";

	public static final String PROFILE_ID = "profile_id";

	public static final String POSITION_ID = "position_id";

	public static final String CREATE_TIME = "create_time";

	public static final String NOTE = "note";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Staff{" +
			"id=" + id +
			", userId=" + userId +
			", deptId=" + deptId +
			", workAge=" + workAge +
			", hireDate=" + hireDate +
			", leaveDate=" + leaveDate +
			", status=" + status +
			", profileId=" + profileId +
			", positionId=" + positionId +
			", createTime=" + createTime +
			", note=" + note +
			"}";
	}
}
