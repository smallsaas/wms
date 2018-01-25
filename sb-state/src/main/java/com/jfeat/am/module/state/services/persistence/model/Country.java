package com.jfeat.am.module.state.services.persistence.model;

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
 * @since 2018-01-25
 */
public class Country extends Model<Country> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * Country Code
     */
	private String code;
    /**
     * Of Language(zh,en)
     */
	private String lang;
    /**
     * Country Name
     */
	private String name;
    /**
     * Full Country Name
     */
	@TableField("full_name")
	private String fullName;


	public Long getId() {
		return id;
	}

	public Country setId(Long id) {
		this.id = id;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Country setCode(String code) {
		this.code = code;
		return this;
	}

	public String getLang() {
		return lang;
	}

	public Country setLang(String lang) {
		this.lang = lang;
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

	public static final String ID = "id";

	public static final String CODE = "code";

	public static final String LANG = "lang";

	public static final String NAME = "name";

	public static final String FULL_NAME = "full_name";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Country{" +
			"id=" + id +
			", code=" + code +
			", lang=" + lang +
			", name=" + name +
			", fullName=" + fullName +
			"}";
	}
}
