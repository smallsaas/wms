package com.jfeat.am.module.organization.services.persistence.model;

import java.io.Serializable;

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
@TableName("t_department_staff")
public class DepartmentStaff extends Model<DepartmentStaff> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 部门ID
     */
	@TableField("department_id")
	private Long departmentId;
    /**
     * 员工ID
     */
	@TableField("staff_id")
	private Long staffId;
    /**
     * 是否主管
     */
	@TableField("is_manager")
	private String isManager;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public static final String ID = "id";

	public static final String DEPARTMENT_ID = "department_id";

	public static final String STAFF_ID = "staff_id";

	public static final String IS_MANAGER = "is_manager";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DepartmentStaff{" +
			"id=" + id +
			", departmentId=" + departmentId +
			", staffId=" + staffId +
			", isManager=" + isManager +
			"}";
	}
}
