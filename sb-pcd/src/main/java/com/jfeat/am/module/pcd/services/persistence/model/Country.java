package com.jfeat.am.module.pcd.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
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
public class Country extends Model<Country> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	@TableField("full_name")
	private String fullName;
	@TableField("english_name")
	private String englishName;
	private String code;


	public Long getId() {
		return id;
	}

	public Country setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Country setName(String name) {
		this.name = name;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public Country setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getEnglishName() {
		return englishName;
	}

	public Country setEnglishName(String englishName) {
		this.englishName = englishName;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Country setCode(String code) {
		this.code = code;
		return this;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String FULL_NAME = "full_name";

	public static final String ENGLISH_NAME = "english_name";

	public static final String CODE = "code";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Country{" +
			"id=" + id +
			", name=" + name +
			", fullName=" + fullName +
			", englishName=" + englishName +
			", code=" + code +
			"}";
	}
}
