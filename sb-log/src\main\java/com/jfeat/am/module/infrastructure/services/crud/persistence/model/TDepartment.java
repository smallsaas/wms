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
@TableName("t_department")
public class TDepartment extends Model<TDepartment> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 所属分公司
     */
	@TableField("org_id")
	private Long orgId;
    /**
     * 排序
     */
	private Integer sort;
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
     * 备注
     */
	private String note;
    /**
     * 版本（乐观锁保留字段）
     */
	private Integer version;


	public Long getId() {
		return id;
	}

	public TDepartment setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public TDepartment setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public TDepartment setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Long getPid() {
		return pid;
	}

	public TDepartment setPid(Long pid) {
		this.pid = pid;
		return this;
	}

	public String getName() {
		return name;
	}

	public TDepartment setName(String name) {
		this.name = name;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public TDepartment setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getNote() {
		return note;
	}

	public TDepartment setNote(String note) {
		this.note = note;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public TDepartment setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public static final String ID = "id";

	public static final String ORG_ID = "org_id";

	public static final String SORT = "sort";

	public static final String PID = "pid";

	public static final String NAME = "name";

	public static final String FULL_NAME = "full_name";

	public static final String NOTE = "note";

	public static final String VERSION = "version";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TDepartment{" +
			"id=" + id +
			", orgId=" + orgId +
			", sort=" + sort +
			", pid=" + pid +
			", name=" + name +
			", fullName=" + fullName +
			", note=" + note +
			", version=" + version +
			"}";
	}
}
