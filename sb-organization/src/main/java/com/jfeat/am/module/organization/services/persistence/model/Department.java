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
 * @author Code Generator
 * @since 2017-10-27
 */
@TableName("t_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * ????
     */
	private String code;
    /**
     * ????
     */
	@TableField("is_org")
	private Integer isOrg;
	private Long pid;
    /**
     * ??
     */
	private String name;
    /**
     * ??
     */
	@TableField("full_name")
	private String fullName;
    /**
     * ??
     */
	private String location;
    /**
     * ??
     */
	private String note;
    /**
     * ???
     */
	private Integer sort;
    /**
     * ???????????
     */
	private Integer version;


	public Long getId() {
		return id;
	}

	public Department setId(Long id) {
		this.id = id;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Department setCode(String code) {
		this.code = code;
		return this;
	}

	public Integer getIsOrg() {
		return isOrg;
	}

	public Department setIsOrg(Integer isOrg) {
		this.isOrg = isOrg;
		return this;
	}

	public Long getPid() {
		return pid;
	}

	public Department setPid(Long pid) {
		this.pid = pid;
		return this;
	}

	public String getName() {
		return name;
	}

	public Department setName(String name) {
		this.name = name;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public Department setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public Department setLocation(String location) {
		this.location = location;
		return this;
	}

	public String getNote() {
		return note;
	}

	public Department setNote(String note) {
		this.note = note;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public Department setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public Department setVersion(Integer version) {
		this.version = version;
		return this;
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
