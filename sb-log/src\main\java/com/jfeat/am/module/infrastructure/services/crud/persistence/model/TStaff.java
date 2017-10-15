package com.jfeat.am.module.infrastructure.services.crud.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2017-10-15
 */
@TableName("t_staff")
public class TStaff extends Model<TStaff> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 所属分公司
     */
	@TableField("org_id")
	private Long orgId;
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
	@TableField("profile_id")
	private Long profileId;
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

	public TStaff setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public TStaff setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public Long getDeptId() {
		return deptId;
	}

	public TStaff setDeptId(Long deptId) {
		this.deptId = deptId;
		return this;
	}

	public Integer getWorkAge() {
		return workAge;
	}

	public TStaff setWorkAge(Integer workAge) {
		this.workAge = workAge;
		return this;
	}

	public Long getProfileId() {
		return profileId;
	}

	public TStaff setProfileId(Long profileId) {
		this.profileId = profileId;
		return this;
	}

	public Long getPositionId() {
		return positionId;
	}

	public TStaff setPositionId(Long positionId) {
		this.positionId = positionId;
		return this;
	}

	public String getNote() {
		return note;
	}

	public TStaff setNote(String note) {
		this.note = note;
		return this;
	}

	public static final String ID = "id";

	public static final String ORG_ID = "org_id";

	public static final String DEPT_ID = "dept_id";

	public static final String WORK_AGE = "work_age";

	public static final String PROFILE_ID = "profile_id";

	public static final String POSITION_ID = "position_id";

	public static final String NOTE = "note";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TStaff{" +
			"id=" + id +
			", orgId=" + orgId +
			", deptId=" + deptId +
			", workAge=" + workAge +
			", profileId=" + profileId +
			", positionId=" + positionId +
			", note=" + note +
			"}";
	}
}
