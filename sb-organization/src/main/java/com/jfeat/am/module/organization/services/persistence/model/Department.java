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
@TableName("t_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 部门编号
     */
	private String code;
    /**
     * 是否机构
     */
	@TableField("is_org")
	private Integer isOrg;
	private Long pid;
    /**
     * 简称
     */
	private String name;
    /**
     * 全称
     */
	@TableField("full_name")
	private String fullName;
    /**
     * 位置
     */
	private String location;
    /**
     * 备注
     */
	private String note;
    /**
     * 排序号
     */
	private Integer sort;
    /**
     * 版本（乐观锁保留字段）
     */
	private Integer version;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsOrg() {
		return isOrg;
	}

	public void setIsOrg(Integer isOrg) {
		this.isOrg = isOrg;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public static final String ID = "id";

	public static final String CODE = "code";

	public static final String IS_ORG = "is_org";

	public static final String PID = "pid";

	public static final String NAME = "name";

	public static final String FULL_NAME = "full_name";

	public static final String LOCATION = "location";

	public static final String NOTE = "note";

	public static final String SORT = "sort";

	public static final String VERSION = "version";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Department{" +
			"id=" + id +
			", code=" + code +
			", isOrg=" + isOrg +
			", pid=" + pid +
			", name=" + name +
			", fullName=" + fullName +
			", location=" + location +
			", note=" + note +
			", sort=" + sort +
			", version=" + version +
			"}";
	}
}
