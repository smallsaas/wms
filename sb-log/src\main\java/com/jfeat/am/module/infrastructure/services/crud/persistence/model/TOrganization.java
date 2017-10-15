package com.jfeat.am.module.infrastructure.services.crud.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("t_organization")
public class TOrganization extends Model<TOrganization> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private Long pid;
    /**
     * 公司名称
     */
	private String name;
    /**
     * 公司地址
     */
	private String address;
    /**
     * 公司介绍
     */
	private String introduce;


	public Long getId() {
		return id;
	}

	public TOrganization setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getPid() {
		return pid;
	}

	public TOrganization setPid(Long pid) {
		this.pid = pid;
		return this;
	}

	public String getName() {
		return name;
	}

	public TOrganization setName(String name) {
		this.name = name;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public TOrganization setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getIntroduce() {
		return introduce;
	}

	public TOrganization setIntroduce(String introduce) {
		this.introduce = introduce;
		return this;
	}

	public static final String ID = "id";

	public static final String PID = "pid";

	public static final String NAME = "name";

	public static final String ADDRESS = "address";

	public static final String INTRODUCE = "introduce";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TOrganization{" +
			"id=" + id +
			", pid=" + pid +
			", name=" + name +
			", address=" + address +
			", introduce=" + introduce +
			"}";
	}
}
