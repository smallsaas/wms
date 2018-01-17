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
 * @author admin
 * @since 2018-01-17
 */
@TableName("t_staff")
public class Staff extends Model<Staff> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 所属部门
     */
	@TableField("dept_id")
	private Long deptId;
    /**
     * 工龄
     */
	@TableField("work_age")
	private Integer workAge;
    /**
     * 入职时间
     */
	@TableField("hire_date")
	private Date hireDate;
    /**
     * 离职时间
     */
	@TableField("leave_date")
	private Date leaveDate;
    /**
     * 在职状态
     */
	private String status;
	@TableField("profile_id")
	private Long profileId;
    /**
     * 所属职位
     */
	@TableField("position_id")
	private Long positionId;
    /**
     * 用户ID, 如果没有关联则为NULL
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 备注
     */
	private String note;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Integer getWorkAge() {
		return workAge;
	}

	public void setWorkAge(Integer workAge) {
		this.workAge = workAge;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static final String ID = "id";

	public static final String DEPT_ID = "dept_id";

	public static final String WORK_AGE = "work_age";

	public static final String HIRE_DATE = "hire_date";

	public static final String LEAVE_DATE = "leave_date";

	public static final String STATUS = "status";

	public static final String PROFILE_ID = "profile_id";

	public static final String POSITION_ID = "position_id";

	public static final String USER_ID = "user_id";

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
			", deptId=" + deptId +
			", workAge=" + workAge +
			", hireDate=" + hireDate +
			", leaveDate=" + leaveDate +
			", status=" + status +
			", profileId=" + profileId +
			", positionId=" + positionId +
			", userId=" + userId +
			", createTime=" + createTime +
			", note=" + note +
			"}";
	}
}
