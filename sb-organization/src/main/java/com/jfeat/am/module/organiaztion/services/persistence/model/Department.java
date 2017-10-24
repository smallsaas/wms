package com.jfeat.am.module.organiaztion.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-10-11
 */
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 排序
     */
	private Long sort;
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
     * 提示
     */
	private String desc;
    /**
     * 版本（乐观锁保留字段）
     */
	private Integer version;


	public Long getId() {
		return id;
	}

	public Department setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSort() {
		return sort;
	}

	public Department setSort(Long sort) {
		this.sort = sort;
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

	public String getDesc() {
		return desc;
	}

	public Department setDesc(String desc) {
		this.desc = desc;
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

	public static final String SORT = "sort";

	public static final String PID = "pid";

	public static final String NAME = "name";

	public static final String FULL_NAME = "full_name";

	public static final String DESC = "desc";

	public static final String VERSION = "version";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Department{" +
			"id=" + id +
			", sort=" + sort +
			", pid=" + pid +
			", name=" + name +
			", fullName=" + fullName +
			", desc=" + desc +
			", version=" + version +
			"}";
	}
}