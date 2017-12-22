package com.jfeat.am.module.pcd.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-22
 */
public class Pcd extends Model<Pcd> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String type;
	private Long pid;


	public Long getId() {
		return id;
	}

	public Pcd setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Pcd setName(String name) {
		this.name = name;
		return this;
	}

	public String getType() {
		return type;
	}

	public Pcd setType(String type) {
		this.type = type;
		return this;
	}

	public Long getPid() {
		return pid;
	}

	public Pcd setPid(Long pid) {
		this.pid = pid;
		return this;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String TYPE = "type";

	public static final String PID = "pid";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Pcd{" +
			"id=" + id +
			", name=" + name +
			", type=" + type +
			", pid=" + pid +
			"}";
	}
}
