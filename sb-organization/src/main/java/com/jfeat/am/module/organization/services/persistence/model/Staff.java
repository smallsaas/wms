package com.jfeat.am.module.organization.services.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */
public class Staff extends Model<Staff> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	@TableField("dept_id")
	private Long deptId;
	@TableField("profile_id")
	private Long profileId;
    /**
     * 工龄
     */
	@TableField("work_age")
	private Integer workAge;
    /**
     * 所属职位
     */
	@TableField("position_id")
	private Long positionId;
    /**
     * 备注
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

	public Integer getWorkAge() {
		return workAge;
	}

	public Staff setWorkAge(Integer workAge) {
		this.workAge = workAge;
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

	public static final String DEPT_ID = "dept_id";

	public static final String PROFILE_ID = "profile_id";

	public static final String WORK_AGE = "work_age";

	public static final String POSITION = "position";

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
			", profileId=" + profileId +
			", workAge=" + workAge +
			", positionId=" + positionId +
			", note=" + note +
			"}";
	}

}
